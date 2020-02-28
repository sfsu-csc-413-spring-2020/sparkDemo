package builder;

import java.util.Date;

public class ResponseBuilder {

  private String name;
  private Date date;

  public ResponseBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public ResponseBuilder setDate(Date date) {
    this.date = date;
    return this;
  }

  public ResponseDto build(){
    return new ResponseDto(this.name, this.date);
  }
}
