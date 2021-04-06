import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

public final class Period implements Serializable {

  private final Date start;
  private final Date end;

  public Period(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  //자바의 직렬화 시스템이 바깥 클래스의 인스턴스 말고 SerializationProxy의 인스턴스를 반환하게 하는 역할
  //이 메서드 덕분에 직렬화 시스템은 바깥 클래스의 직렬화된 인스턴스를 생성해낼 수 없다
  private Object writeReplace() {
    return new SerializationProxy(this);
  }

  private void readObject(ObjectInputStream stream) throws InvalidObjectException {
    throw new InvalidObjectException("프록시가 필요합니다");
  }

  //바깥 클래스의 논리적 상태를 정밀하게 표현하는 중첩 클래스(Period의 직렬화 프록시)
  private static class SerializationProxy implements Serializable {

    private static final long serialVersionUID = 4892068902382L;

    private final Date start;
    private final Date end;

    //생성자는 단 하나여야 하고, 바깥 클래스의 인스턴스를 매개변수로 받고 데이터를 복사해야 함
    SerializationProxy(Period p) {
      this.start = p.start;
      this.end = p.end;
    }

    //역직렬화 대상 교체
    private Object readResolve() {
      return new Period(start, end);
    }
  }
}