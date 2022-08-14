package hello.hellospring.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
//      스프링에서 html form안에 name과 일치하는 데이터를 받아 줌
        this.name = name;
    }



        
}
