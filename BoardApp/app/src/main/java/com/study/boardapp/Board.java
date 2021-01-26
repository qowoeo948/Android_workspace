package com.study.boardapp;

import android.os.Parcel;
import android.os.Parcelable;

//이 VO는 다른 객체에게 전달 시, 혹은 전달받은 데이터를 처리하기 위해
//Pacelable 인터페이스를 구현해야 한다..
public class Board implements Parcelable{
    private int board_id;
    private String title;
    private String writer;
    private String content;
    private String regdate;
    private int hit;

    public Board(){

    }

    //인수있는 생성자 추가됨
    //직렬화 되어 전달된 데이터를 다시 복원시킴..
    protected Board(Parcel in) {
        board_id = in.readInt();
        title = in.readString();
        writer = in.readString();
        content = in.readString();
        regdate = in.readString();
        hit = in.readInt();
    }

    public static final Creator<Board> CREATOR = new Creator<Board>() {
        @Override
        public Board createFromParcel(Parcel in) {
            return new Board(in);
        }

        @Override
        public Board[] newArray(int size) {
            return new Board[size];
        }
    };

    //맴버변수 쪼개서 저장해놓음  (데이터 전송 직전에, 직렬화(=serialize)(직렬화=데이터는 보낼때 일렬로 늘려서 보내야함) 시킴)
    //전송하기 위한 준비작업
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(board_id);
        dest.writeString(title);
        dest.writeString(writer);
        dest.writeString(content);
        dest.writeString(regdate);
        dest.writeInt(hit);
    }

    @Override
    public int describeContents() {
        return 0;
    }



    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }


}
