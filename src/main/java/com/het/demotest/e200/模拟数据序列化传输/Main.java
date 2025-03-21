package com.het.demotest.e200.模拟数据序列化传输;

import java.util.ArrayList;
import java.util.List;

class SerializeTool {

    static class Node {
        int pos;    //位置
        int type;   //类型
        String value;   //值
        Node composeHead;   //对于Compose，存储下一层的各个块数据
        Node next;  //指向下一个块的数据

        Node() {
            this.pos = 0;
            this.type = -1;
            this.value = "";
            this.composeHead = null;
            this.next = null;
        }

        //输出解码
        String toEncode() {
            if (type == -1) {
                return "";  //未知格式，返回空
            }
            if (type != 2) {
                //数字或者字符串 直接按格式返回
                return pos + "#" + type + "#" + value.length() + "#" + value;
            }
            //Compose格式 遍历composeHead中的所有节点将所有编码连接起来
            StringBuilder composeValue = new StringBuilder();
            Node p = composeHead;
            while (p != null) {
                composeValue.append(p.toEncode());
                p = p.next;
            }
            //按格式返回
            return pos + "#" + type + "#" + composeValue.length() + "#" + composeValue.toString();
        }

        //输出解码
        String toDecode() {
            if (type == -1) {
                return "";  //未知格式，返回空
            }
            if (type != 2) {
                String typeStr = (type == 0) ? "Integer" : "String";
                //数字或者字符串 直接按格式返回
                return "[" + pos + "," + typeStr + "," + value + "]";
            }

            StringBuilder composeValue = new StringBuilder("[" + pos + ",Compose,");
            //Compose格式 遍历composeHead中的所有节点将所有解码连接起来
            Node p = composeHead;
            while (p != null) {
                composeValue.append(p.toDecode()).append(",");
                p = p.next;
            }
            composeValue.setLength(composeValue.length() - 1);
            return composeValue + "]";
        }
    }

    private Node linkList;

    public SerializeTool() {
        this.linkList = null;   //构造函数，初始化linkList
    }

    //获取编码
    public String toEncode(String data) {
        //拆分编码得到数据块
        List<String> words = new ArrayList<>();
        if (splitEncode(data, words)) {
            return "ENCODE_ERROR";
        }
        //对数据块进行处理，构造链表
        linkList = createEncodeLinkList(words, 0, new boolean[]{false});
        if (linkList == null) {
            return "ENCODE_ERROR";
        }
        //处理编码
        StringBuilder allDataEncode = new StringBuilder();
        Node p = linkList;
        while (p != null) {
            allDataEncode.append(p.toEncode());
            p = p.next;
        }
        return allDataEncode.toString();
    }

    //获取解码
    public String toDecode(String data) {
        //拆分解码得到数据块
        List<String> words = new ArrayList<>();
        if (splitDecode(data, words)) {
            return "DECODE_ERROR";
        }
        //对数据块进行处理，构造链表
        linkList = createDecodeLinkList(words, 0, new boolean[]{false});
        if (linkList == null) {
            return "DECODE_ERROR";
        }
        //处理解码
        StringBuilder allDataDecode = new StringBuilder();
        Node p = linkList;
        while (p != null) {
            String code = p.toDecode();
            if (!code.isEmpty()) {
                allDataDecode.append(code).append(",");
            }
            p = p.next;
        }
        if (allDataDecode.length() > 0) {
            allDataDecode.setLength(allDataDecode.length() - 1);    //去掉最后一个多余的逗号
        }
        return allDataDecode.toString();
    }

    //检查字符串str是否为数字，num保存str转为数字的结果 返回值为是否为数字，是的话返回true
    private boolean checkIsNumber(String str, int[] num) {
        try {
            num[0] = Integer.parseInt(str);
            return Integer.toString(num[0]).equals(str);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //检查字符串str是否合法 存在非法字符时返回false
    private boolean checkString(String str) {
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(Character.isDigit(ch) || Character.isLetter(ch) || ch == ' ') {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }

    //编码拆分数据 按照[]分割 出现问题时返回true
    private boolean splitEncode(String str, List<String> words) {
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '[') {
                StringBuilder word = new StringBuilder();
                int pos = i + 1;
                int level = 1;  //方框号的层数
                while (pos < str.length()) {
                    if (str.charAt(pos) == '[') {
                        level++;    //遇到左方括号，层数+1
                    } else if (str.charAt(pos) == ']') {
                        level--;    //遇到右方括号，层数-1
                        if (level == 0) break;  //如果层数为0，代表完整了，跳出循环
                    }
                    word.append(str.charAt(pos));
                    pos++;
                }
                if (pos >= str.length() || str.charAt(pos) != ']') {
                    return true; //检查当前位置是不是右括号
                }
                pos++;
                if (pos < str.length() && str.charAt(pos) != ',') {
                    return true;  //如果下一个位置还有数据时，检验是不是逗号
                }
                i = pos + 1;
                words.add(word.toString()); //将分割结果放到words中
            } else {
                return true;    //数据起始不是左方括号，报错
            }
        }
        return false;
    }

    //解码拆分数据 按照#分割 出现问题时返回true
    private boolean splitDecode(String str, List<String> words) {
        int l = 0, r;   //lr分别标记每块完整的数据的左右位置
        while (l < str.length()) {
            r = l;
            int count = 0;  //已遇到的#个数
            int len = 0;    //值的长度
            while (r < str.length() && count < 2) { //先跳过前两个#之前的数据（位置和类型）
                if (str.charAt(r) == '#') {
                    count++;
                }
                r++;
            }
            while (r < str.length()) {  //读取值的长度
                if (str.charAt(r) == '#') {
                    break;    //#代表记录长度的块结束了 跳出循环
                }
                if (str.charAt(r) < '0' || str.charAt(r) > '9') {
                    return true;    //内容不是数字，报错
                }
                len = len * 10 + (str.charAt(r) - '0');
                r++;
            }
            if (len <= 0) {
                return true;  //处理出来的长度不是正数，报错
            }
            r += len;   //后面的len个字符都是数据
            if (r >= str.length()) {
                return true; //越界了，报错
            }
            words.add(str.substring(l, r + 1)); //将整块的数据存到words
            l = r + 1;  //l指向下一个未处理的位置
        }
        return false;
    }

    //解码时，创建链表
    private Node createEncodeLinkList(List<String> words, int pos, boolean[] isError) {
        if (pos >= words.size()) {
            return null;   //越界了，返回空指针
        }
        Node newNode = new Node();  //创建节点
        if (makeEncodeNode(words.get(pos), newNode)) {  //对当前值构造节点
            //构造成功则创建下一个节点
            newNode.next = createEncodeLinkList(words, pos + 1, isError);
            if (isError[0]) {
                //构造失败了，返回空指针
                return null;
            }
            //构造成功，返回本节点
            return newNode;
        } else {
            //当前节点构造失败，返回空指针
            isError[0] = true;
            return null;
        }
    }

    private Node createDecodeLinkList(List<String> words, int pos, boolean[] isError) {
        if (pos >= words.size()) {
            return null;   //越界了，返回空指针
        }
        Node newNode = new Node();  //创建节点
        if (makeDecodeNode(words.get(pos), newNode)) {  //对当前值构造节点
            newNode.next = createDecodeLinkList(words, pos + 1, isError);
            if (isError[0]) {
                //构造失败了，返回空指针
                return null;
            }
            //构造成功，返回本节点
            return newNode;
        } else {
            //当前节点构造失败，返回空指针
            isError[0] = true;
            return null;
        }
    }

    //根据编码构造节点 未成功返回false
    private boolean makeEncodeNode(String word, Node node) {
        int dPos = word.indexOf(',');   //找到第一个逗号
        if (dPos == -1) {
            return false;   //未找到，报错
        }
        int[] pos = new int[1];
        if (!checkIsNumber(word.substring(0, dPos), pos)) {
            return false; //检查位置是否为数字
        }
        node.pos = pos[0];

        int dPos2 = word.indexOf(',', dPos + 1);    //找到第二个逗号
        if (dPos2 == -1) {
            return false;  //未找到，报错
        }
        String typeStr = word.substring(dPos + 1, dPos2);   //类型

        node.value = word.substring(dPos2 + 1); //值
        if (typeStr.equals("String")) {
            node.type = 1;  //字符串类型，设置type
            //检查值是否合法
            if(!checkString(node.value)) {
                node.type = -1; //出现不合法字符 忽略该节点
            }
        } else if (typeStr.equals("Integer")) {
            node.type = 0;  //数字类型，设置type
            int[] nVal = new int[1];
            //检查值是否是数字
            if (!checkIsNumber(node.value, nVal)) {
                node.type = -1; //不是数字 忽略该节点
            }
        } else if (typeStr.equals("Compose")) {
            node.type = 2;  //Compose类型，设置type

            //对值进行创建链表操作，值赋给composeHead
            //如果创建出错 则报错
            List<String> words = new ArrayList<>();
            if (splitEncode(node.value, words)) {
                return false;
            }
            boolean[] isError = new boolean[]{false};
            node.composeHead = createEncodeLinkList(words, 0, isError);
            if (isError[0]) {
                return false;
            }
        } else {
            node.type = -1; //其它类型，设置type
        }
        return true;
    }

    //根据解码构造节点 未成功返回false
    private boolean makeDecodeNode(String word, Node node) {
        int l = 0, r = 0;   //lr为当前处理的区间左右端点
        while (r < word.length() && word.charAt(r) != '#') {
            r++; //找到第一个#
        }
        if (r >= word.length() || r == l) {
            return false; //未找到，报错
        }
        int[] pos = new int[1];
        if (!checkIsNumber(word.substring(l, r), pos)) {    //检查位置是否是数字
            node.type = -1; //不是数字，忽略该节点
            return true;
        }
        node.pos = pos[0];  //位置

        l = r + 1;
        r = l;
        while (r < word.length() && word.charAt(r) != '#') {
            r++; //找到第二个#
        }
        if (r >= word.length() || r == l) {
            return false; //未找到，报错
        }
        int[] type = new int[1];
        if (!checkIsNumber(word.substring(l, r), type)) {   //检查类型是否是数字
            node.type = -1;
            return true;
        }
        node.type = type[0];    //类型
        if (node.type < 0 || node.type > 2)  {
            node.type = -1;
            return true;
        }

        l = r + 1;
        r = l;
        while (r < word.length() && word.charAt(r) != '#') {
            r++; //找到第三个#
        }
        if (r >= word.length() || r == l) {
            return false; //未找到，报错
        }
        int[] len = new int[1];
        if (!checkIsNumber(word.substring(l, r), len)) {
            return false;    //检查长度是否是数字
        }

        l = r + 1;
        if (l + len[0] > word.length()) {
            return false;   //越界了，报错
        }
        node.value = word.substring(l, l + len[0]); //值

        if (node.type == 1) {
            //检查值是否合法
            if (!checkString(node.value)) {
                node.type = -1; //出现不合法字符 忽略该节点
            }
        }
        else if (node.type == 0) {
            int[] value = new int[1];
            if(!checkIsNumber(node.value, value)) { //检查值是否为数字
                node.type = -1;
            }
        }
        else if (node.type == 2) {
            //对值进行创建链表操作，值赋给composeHead
            //如果创建出错 则报错
            List<String> words = new ArrayList<>();
            if (splitDecode(node.value, words)) {
                return false;
            }
            boolean[] isError = new boolean[]{false};
            node.composeHead = createDecodeLinkList(words, 0, isError);
            if (isError[0]) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int type = scanner.nextInt();
        scanner.nextLine();
        String str = scanner.nextLine();
        SerializeTool tool = new SerializeTool();
        String ans;
        if (type == 1) {
            ans = tool.toEncode(str);
        } else {
            ans = tool.toDecode(str);
        }
        System.out.println(ans);
        scanner.close();
    }
}