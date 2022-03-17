package com.fastcampus.ch4.domain;

import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class BoardDto {
    private Integer bno;
    @NonNull private String  title;
    @NonNull private String  content;
    @NonNull private String  writer;
    private int     view_cnt;
    private int     comment_cnt;
    private Date    reg_date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(bno, boardDto.bno) && Objects.equals(title, boardDto.title) && Objects.equals(content, boardDto.content) && Objects.equals(writer, boardDto.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, title, content, writer);
    }
}
