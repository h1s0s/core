package hello.core.singleton;

public class StatefulService {

    /*
        싱글톤 컨테이너 주의점
        - 특정 클라이언트에 의존적인 필드가 있으면 안된다
        - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다
        - 가급적 읽기만 가능해야 한다
        - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, threadLocal등을 사용해야한다
        - 스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생 할 수 있다
    */
     private int price; //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기서 문제가 발생함!
    }

    public int getPrice() {
        return price;
    }

}
