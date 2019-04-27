package com.pragati.libmange.librarymanagement.vo;

import com.pragati.libmange.librarymanagement.vo.enums.ReaderTypeEnum;

public class ReaderVO {

    private String readerId;
    private String password;
    private ReaderTypeEnum readerType;
    private String name;
    private String address;
    private int numOfCopiesBorrowed;

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ReaderTypeEnum getReaderType() {
        return readerType;
    }

    public void setReaderType(ReaderTypeEnum readerType) {
        this.readerType = readerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumOfCopiesBorrowed() {
        return numOfCopiesBorrowed;
    }

    public void setNumOfCopiesBorrowed(int numOfCopiesBorrowed) {
        this.numOfCopiesBorrowed = numOfCopiesBorrowed;
    }
}
