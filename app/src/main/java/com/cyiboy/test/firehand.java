package com.cyiboy.test;

public class firehand {
    private String a,b,c,d,question,answer;
    private long no;
    public  firehand() {}

    public firehand(String a, String b, String c, String d, String question, String answer, long no) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.question = question;
        this.answer = answer;
        this.no = no;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }
}
