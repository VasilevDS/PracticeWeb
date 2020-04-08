CREATE table USER_WEB
(
    ID NUMBER,
    NAME VARCHAR2(100) not null,
    SURNAME VARCHAR2(100) not null,
    LOGIN VARCHAR2(100) not null,
    PASSWORD VARCHAR2(100) not null,
    constraint USER_PK primary key (ID)
);

CREATE sequence USER_WEB_ID_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE trigger USER_WEB_ID_TRIG
    before insert on DUMMY.USER_WEB
    for each row
begin
    select USER_WEB_ID_SEQ.nextval into :NEW.id from dual;
end;