package com.cogon_k;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Memo extends PanacheEntityBase {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(insertable = false, nullable = false, updatable = false)
    public String id;
    public int xStart;
    public int yStart;
    public int xEnd;
    public int yEnd;
    public MemoType type;
    public String content;
    public int fontSize;
    public String fontColor;
    public String color;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    public Chizu chizu;

    public Memo() {}

    public Memo(int xStart, int yStart, int xEnd, int yEnd, MemoType type, String content, int fontSize, String fontColor, String color, Chizu chizu) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.type = type;
        this.content = content;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.color = color;
        this.chizu = chizu;
    }
}
