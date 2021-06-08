create table itsq_user(--用户表
id Varchar2(20) primary key, -- 用户id 唯一
name Varchar2(30), -- 用户名
password  Varchar2(30),--密码
glystatus char(1),--管理员判断
sex  char(1) not null,--性别
birthtime date,--出生日期
unitname Varchar2(100),--单位名称
jobpost Varchar2(30),--工作职位
maritalstatus VARCHAR2(5),--婚姻状况
introduce Varchar2(2000),--自我介绍
motto Varchar2(300),--座右铭
likeskill Varchar2(5),--感兴趣的语言
headimage Varchar2(200)--头像地址
);

create table itsq_user(--帖子信息表
ID Varchar2(20) primary key, -- 帖子id 唯一
MAIN_ID Varchar2(20),
TITLE Varchar2(2000),
CONTENT Varchar2(2000),
STATUS Varchar2(1),
CREATE_ID Varchar2(20),
CREATE_TIME date,
UPDATE_ID Varchar2(20),
UPDATE_TIME date,
KILL_TYPE Varchar2(20)
);
create table itsq_userlx(//用户联系方式
id Varchar2(20) primary key, -- 用户编号（唯一）
welx  Varchar2(60),
qqlx  Varchar2(20),
tellx  Varchar2(20),
bklx  Varchar2(60)
);
create table itsq_POSTZP(//用户联系方式
ID Varchar2(20) primary key,
ZAN_COUNT NUMBER,
PL_COUNT NUMBER,
SC_COUNT NUMBER,
ZF_COUNT NUMBER
};
create table itsq_opinions(--反馈表
id Varchar2(20) primary key,--信息Id
userid Varchar2(20) , -- 用户id
opinions Varchar2(300), -- 内容
time  date,--时间
status char(1)--状态
);
create sequence BPID_sq
increment by 1
start with 1
maxvalue 9999
minvalue 1
nocycle
cache 20;
select  BPID_sq.nextval from dual;

create sequence BUID_sq
increment by 1
start with 1
maxvalue 9999
minvalue 1
nocycle
cache 20;
select  BUID_sq.nextval from dual;