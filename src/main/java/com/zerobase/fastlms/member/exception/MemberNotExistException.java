package com.zerobase.fastlms.member.exception;

public class MemberNotExistException extends RuntimeException {
    public MemberNotExistException(String error) {
        super(error);
    }
}
