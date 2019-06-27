package com.interview.interview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "X")
public class RecordModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "A")
  private String aColumn;

  @Column(name = "B")
  private String bColumn;

  @Column(name = "C")
  private String cColumn;

  @Column(name = "D")
  private String dColumn;

  @Column(name = "E")
  @Size(max = 9999)
  private String eColumn;

  @Column(name = "F")
  private String fColumn;

  @Column(name = "G")
  private String gColumn;

  @Column(name = "H")
  private String hColumn;

  @Column(name = "I")
  private String iColumn;

  @Column(name = "J")
  private String jColumn;

  public String getColumn(int index) {
    switch (index) {
      case 1:
        return this.aColumn;
      case 2:
        return this.bColumn;
      case 3:
        return this.cColumn;
      case 4:
        return this.dColumn;
      case 5:
        return this.eColumn;
      case 6:
        return this.fColumn;
      case 7:
        return this.gColumn;
      case 8:
        return this.hColumn;
      case 9:
        return this.iColumn;
      case 10:
        return this.jColumn;
      default:
        break;
    }
    return null;
  }

  public void setColumn(int index, String token) {
    switch (index) {
      case 1:
        this.setAColumn(token);
      case 2:
        this.setBColumn(token);
      case 3:
        this.setCColumn(token);
      case 4:
        this.setDColumn(token);
      case 5:
        this.setEColumn(token);
      case 6:
        this.setFColumn(token);
      case 7:
        this.setGColumn(token);
      case 8:
        this.setHColumn(token);
      case 9:
        this.setIColumn(token);
      case 10:
        this.setJColumn(token);
      default:
        break;
    }
  }
}


