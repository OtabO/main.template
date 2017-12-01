package util.template.Calculator;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangsx on 2017/11/29.
 * 后缀表达式
 * 中缀表达式的正确性由外部保证，如果是错误的表达式，则会在运行期抛出运行时异常
 */
public class SuffixExpression {

    /**
     * 中缀表达式
     */
    private String infixExpression;

    /**
     * 分类过的中缀表达式
     */
    private List sortedChain;

    /**
     * 可计算的后缀表达式
     */
    private List infixExpressionChain;

    public SuffixExpression(String infixExpression) {
        this.infixExpression = infixExpression.trim();
    }

    /**
     * 中缀表达式验证
     *
     * @throws IllegalArgumentException
     */
    public void check() throws IllegalArgumentException {
        if (this.infixExpression == null || this.infixExpression.trim().length() == 0)
            throw new IllegalArgumentException("中缀表达式不能为空");

    }

    /**
     * 创建后缀表达式
     *
     * @throws IllegalArgumentException
     */
    public void build() throws IllegalArgumentException {
        check();
        sort();
        doBuild0();
    }

    /**
     * 计算后缀表达式
     * @return
     */
    public Object calculate(){
        if(infixExpressionChain==null||infixExpressionChain.size()==0){
            throw new IllegalArgumentException("中缀表达式不能为空");
        }
        Stack<DoubleOperands> tmp=new Stack();
        for(Object obj: infixExpressionChain) {
            if(obj instanceof DoubleOperands){
                tmp.push((DoubleOperands) obj);
                continue;
            }

            Operator currentOp=(Operator) obj;
            DoubleOperands right=tmp.pop();
            DoubleOperands left=tmp.pop();

            DoubleOperands result=null;
            switch (currentOp.getOpStr()){
                case Operator.ADD:
                    result=new DoubleOperands(String.valueOf(left.getVal()+right.getVal()));
                    break;
                case Operator.SUB:
                    result=new DoubleOperands(String.valueOf(left.getVal()-right.getVal()));
                    break;
                case Operator.MUL:
                    result=new DoubleOperands(String.valueOf(left.getVal()*right.getVal()));
                    break;
                case Operator.DIV:
                    result=new DoubleOperands(String.valueOf(left.getVal()/right.getVal()));
                    break;
                default:
                    throw new IllegalArgumentException("非法的操作符");
            }
            tmp.push(result);
        }
        return tmp.pop();
    }

    /**
     * 将中缀表达式字符串格式化
     */
    public void sort() {
        sortedChain = new Sorter().sort(this.infixExpression);
    }

    private void doBuild() {
        if (sortedChain == null||sortedChain.size()==0)
            throw new IllegalArgumentException("中缀表达式中间结果集不能为空");
        List tmp = new ArrayList();
        Stack<Operator> stack = new Stack<>();
        for (int i = 0; i < sortedChain.size(); i++) {
            if (sortedChain.get(i) instanceof Operator) {
                Operator currentOp = (Operator) sortedChain.get(i);
                if (stack.empty()) {
                    stack.push(currentOp);
                    continue;
                }
                Operator opInStack = stack.peek();
                //栈中的操作符优先级较低，当栈顶是(且当前不是)则入栈
                if ((opInStack.equals(Operator.BRACKETS_LEFT) || opInStack.compareTo(currentOp) < 0) && !currentOp.equals(Operator.BRACKETS_RIGHT)) {
                    stack.push(currentOp);
                } else {
                    for (; ; ) {
                        if(stack.isEmpty()){
                            stack.push(currentOp);
                            break;
                        }
                        Operator opInStackTmp = stack.peek();
                        //当前操作符是)需要一直从栈中弹出操作符，直到相应的左括号为止
                        if (currentOp.equals(Operator.BRACKETS_RIGHT)) {
                            if (opInStackTmp.equals(Operator.BRACKETS_LEFT)) {
                                stack.pop();
                                break;
                            }
                            tmp.add(stack.pop());
                        } else {
                            //出栈的原则除了优先级比较外，(不能在当前不是)的情况下出栈
                            if (opInStackTmp.compareTo(currentOp) >= 0 && !opInStackTmp.equals(Operator.BRACKETS_LEFT)) {
                                tmp.add(stack.pop());
                            } else {
                                stack.push(currentOp);
                                break;
                            }
                        }
                    }
                }
            } else {
                tmp.add(sortedChain.get(i));
            }
        }
        int size=stack.size();
        for (int j = 0; j < size; j++) {
            tmp.add(stack.pop());
        }

        infixExpressionChain=tmp;
    }

    /**
     * 中缀表达式转化为后缀表达式步骤
     * 1、遇到操作数：直接输出（添加到后缀表达式中）
     * 2、栈为空时，遇到运算符，直接入栈
     * 3、遇到左括号：将其入栈
     * 4、遇到右括号：执行出栈操作，并将出栈的元素输出，直到弹出栈的是左括号，左括号不输出。
     * 5、遇到其他运算符：加减乘除：弹出所有优先级大于或者等于该运算符的栈顶元素，然后将该运算符入栈（遇到左括号停止出栈）
     * 6、最终将栈中的元素依次出栈，输出。
     */
    private void doBuild0(){
        if (sortedChain == null||sortedChain.size()==0)
            throw new IllegalArgumentException("中缀表达式中间结果集不能为空");
        List tmp = new ArrayList();
        Stack<Operator> stack = new Stack<>();
        for (int i = 0; i < sortedChain.size(); i++) {
            if(sortedChain.get(i) instanceof Operands){
                tmp.add(sortedChain.get(i));
            }else{
                Operator currentOp=(Operator) sortedChain.get(i);
                if(stack.empty()){
                    stack.push(currentOp);
                }else{
                    if(currentOp.equals(Operator.BRACKETS_LEFT)){
                        stack.push(currentOp);
                    }else if(currentOp.equals(Operator.BRACKETS_RIGHT)){
                        for(;;){
                            if(stack.empty()){
                                throw new IllegalArgumentException("后缀表达式结构错误");
                            }
                            Operator topOp=stack.pop();
                            if(topOp.equals(Operator.BRACKETS_LEFT)){
                                break;
                            }
                            tmp.add(topOp);
                        }
                    }else{
                        for(;;){
                            if(stack.empty()){
                                stack.push(currentOp);
                                break;
                            }
                            Operator topOp=stack.peek();
                            if(topOp.compareTo(currentOp)>=0 && !topOp.equals(Operator.BRACKETS_LEFT)){
                                tmp.add(stack.pop());
                            }else{
                                stack.push(currentOp);
                                break;
                            }
                        }
                    }
                }
            }
        }
        int size=stack.size();
        for(int i=0;i<size;i++){
            tmp.add(stack.pop());
        }
        infixExpressionChain=tmp;
    }

    /**
     * 将中缀表达式中的元素挑选出来
     */
    static class Sorter {

        Pattern p = Pattern.compile("[+\\-*/(){}]");

        public List sort(String origin) {
            List chain = new ArrayList();
            Matcher matcher = p.matcher(origin.trim());
            List<Integer> indexList = new ArrayList<>();
            while (matcher.find()) {
                int opIndex = matcher.start();
                indexList.add(opIndex);
            }

            for (int i = 0; i <= indexList.size(); i++) {
                int start = -1;
                int end = 0;
                if (i != 0) {
                    start = indexList.get(i - 1);
                }
                if (i == indexList.size()) {
                    end = origin.length();
                } else {
                    end = indexList.get(i);
                }
                if (end > start) {
                    String operandsStr = origin.substring(start + 1, end);
                    if (operandsStr.trim().length() > 0) {
                        Operands operands = new DoubleOperands(operandsStr);
                        chain.add(operands);
                    }
                }
                if (i != indexList.size()) {
                    String operatorStr = String.valueOf(origin.charAt(indexList.get(i)));
                    if (operatorStr.trim().length() > 0) {
                        Operator operator = new Operator(operatorStr);
                        chain.add(operator);
                    }
                }
            }
            return chain;
        }


    }

    /**
     * 操作数
     */
    static abstract class Operands<T> implements Serializable {

        protected T val;

        protected String ori;

        public Operands(String ori) {
            this.ori = ori;
        }

        @Override
        public String toString() {
            return "Operands{" +
                    "val=" + val +
                    '}';
        }

        abstract T getVal();
    }

    static class DoubleOperands extends Operands<Double> {

        public DoubleOperands(String ori) {
            super(ori);
            build();
        }

        @Override
        Double getVal() {
            return this.val;
        }

        protected void build(){
            this.val = Double.parseDouble(ori);
        }

    }

    /**
     * 操作符
     */
    static class Operator implements Comparable, Serializable {

        public static final String ADD = "+";
        public static final String SUB = "-";
        public static final String MUL = "*";
        public static final String DIV = "/";
        public static final String BRACKETS_LEFT = "(";
        public static final String BRACKETS_RIGHT = ")";

        private static final List<String> opSet = new ArrayList<>();

        static {
            opSet.add(ADD);
            opSet.add(SUB);
            opSet.add(MUL);
            opSet.add(DIV);
            opSet.add(BRACKETS_LEFT);
            opSet.add(BRACKETS_RIGHT);
        }

        private String opStr;

        private Integer priority;

        public Operator(String opStr) {
            if (!opSet.contains(opStr.trim())) {
                throw new IllegalArgumentException("非法的操作符");
            }
            this.opStr = opStr.trim();
            //因为操作符的优先级都是成对的，所以除2
            this.priority = opSet.indexOf(this.opStr)/2;
        }

        public String getOpStr() {
            return opStr;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public int compareTo(Object o) {
            return this.priority-((Operator) o).getPriority() ;
        }

        @Override
        public int hashCode() {
            return opStr.hashCode();
        }

        @Override
        public boolean equals(Object anObject) {
            return opStr.equals(anObject);
        }

        @Override
        public String toString() {
            return "Operator{" +
                    "opStr='" + opStr + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }


    public static void main(String[] args) {
        String ori = "0-(( 4 * 6 + ( 2 + 7 ) / 3)) +1.1  ";
        String ori1 = "1*2*2/2+(1-1)/4";
        String ori2 = "1 ";
        String ori3 = "1 + 2* 3 + (4 * 5 + 6) * 7";
//
        SuffixExpression suffixExpression=new SuffixExpression(ori1);
        suffixExpression.build();
        System.out.println(suffixExpression.calculate());

//        System.out.println(0&0);
    }
}
