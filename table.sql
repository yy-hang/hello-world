create table itsq_user(--�û���
id Varchar2(20) primary key, -- �û�id Ψһ
name Varchar2(30), -- �û���
password  Varchar2(30),--����
glystatus char(1),--����Ա�ж�
sex  char(1) not null,--�Ա�
birthtime date,--��������
unitname Varchar2(100),--��λ����
jobpost Varchar2(30),--����ְλ
maritalstatus VARCHAR2(5),--����״��
introduce Varchar2(2000),--���ҽ���
motto Varchar2(300),--������
likeskill Varchar2(5),--����Ȥ������
headimage Varchar2(200)--ͷ���ַ
);

create table itsq_user(--������Ϣ��
ID Varchar2(20) primary key, -- ����id Ψһ
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
create table itsq_userlx(//�û���ϵ��ʽ
id Varchar2(20) primary key, -- �û���ţ�Ψһ��
welx  Varchar2(60),
qqlx  Varchar2(20),
tellx  Varchar2(20),
bklx  Varchar2(60)
);
create table itsq_POSTZP(//�û���ϵ��ʽ
ID Varchar2(20) primary key,
ZAN_COUNT NUMBER,
PL_COUNT NUMBER,
SC_COUNT NUMBER,
ZF_COUNT NUMBER
};
create table itsq_opinions(--������
id Varchar2(20) primary key,--��ϢId
userid Varchar2(20) , -- �û�id
opinions Varchar2(300), -- ����
time  date,--ʱ��
status char(1)--״̬
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