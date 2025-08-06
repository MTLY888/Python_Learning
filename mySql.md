<h1>mysql基本知识</h1>

<h2>操作文件夹(库)</h2>

<div><span style = "color:red;font-size:30px">增</span><br>
   <code>creat database db1 charset utf8;</code>
</div>

<div><span style = "color:red;font-size:30px">删</span><br>
    <code>drop database db1</code>
</div>

<div><span style = "color:red;font-size:30px">改</span><br>
	<code> alter database db1 charset gbk;</code>
</div>

<div><span style = "color:red;font-size:30px" >查</span><br>
	<code>show create database db1;</code><br>
	<code>show databases;</code>
</div>
<h2>操作文件（表）</h2>

<div><span style = "color:red;font-size:30px" >增</span><br>
<p>
    切换文件夹：<code>use db1;</code>
    </p>
    查看当前文件夹：<code>select database();</code><br>
    增加表:<code>create table t1(id int,name char);</code>
    </div>

<div><span style = "color:red;font-size:30px" >删</span><br>
	删除表:<code>drop table t1;</code>
</div>

<div><span style = "color:red;font-size:30px" >改</span><br>
<code>alter table t1 modify name char(6);</code><br>
</div>

<div><span style = "color:red;font-size:30px" >查</span><br>
	<code>show create table t1;</code><br>
    <code>show tables;</code><br>
	<code>desc t1;</code><br>
    <code>alter table t1 change name NAME char(7);</code>
</div>

<h2>操作文件内容(记录)</h2>

<div><span style = "color:red;font-size:30px" >增</span><br>
	<code>insert t1(id,name) value(1,'Cheng'),(2,'Feng'),(3,'Wang');</code>
</div>

<div><span style = "color:red;font-size:30px" >删</span><br></div>

<div><span style = "color:red;font-size:30px" >改</span><br>
	<code>delete from t1 where id=1;</code>
</div>

<div><span style = "color:red;font-size:30px" >查</span><br>
	<code>select id,name from db1.t1;</code>
    <code>select * from db1.t1;</code>
    <code>update db1.t1 set name="ChengFeng" where id=1;</code>
</div>
<h2>什么是存储引擎</h2>

<div>
    <p>
        存储引擎就是表的类型
    </p>
    <p>
        查看mysql支持的存储引擎:<code>show engines;</code>
    </p>
    <h4>指定表的存储类型/存储引擎：<br></h4>
        <code>
        	create table t1(id int)engine=innodb;<br>
            create table t2(id int)engine=memory;<br>
            create table t3(id int)engine=blackhole;<br>
            create table t4(id int)engine=myisam;<br>
        </code>
    <code>insert into t1 value(1);</code>
</div>

<h2>表的数据类型</h2>

<div>
    <img src="C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250701105155832.png" width="600">
</div>

<h2>日期类型</h2>

<div>
	<code>create table student(
        id int,
        name char(6),
        born_year year,
        birth_date date,
        class_time time,
        reg_time datetime
        );</code><br>
    <code>insert into student values(1,"Cheng",now(),now(),now(),now());</code>
</div>

如果一条命令没有输入完整，则会出现->符号，想要退出的吗，输入\c,如果还是无法退出的话，观察语句缺少那部分，比如缺少 ',想要推出的话输入：'\c。

<h2 >字符类型</h2>

<div>
	<ul>
        <li>char 定长</li>
        <li>varchar 变长</li>
    </ul>
    <p>
        宽度指的是字符的个数。
    </p>
    <code>create table t4(name char(5));<br>
    	  create table t5(name varchar(5));<br>
          insert into t4 value('程');<br>
          insert into t5 value('王');<br>	
          select char_length(name) from t4;<br>
          select char_length(name) from t5;<br>
    </code> 
    <p>
        枚举类型有与集合类型：
    </p>
    <code>create table consumer(
        id int,
        name char(16),
        sex enum('male','female','other'),
        level enum('vip1','vip2','vip3'),
        hobble set('play','music','read','run')
        );<br>
    	insert into consumer value(1,'Cheng','male','vip3','play,run');
    </code><br>
    <p>
        完整性约束：
    </p>
    <code>create table t6(id int,name char(8),sex enum('male','female') not null default 'male');</code>
</div>




末尾去除空格查询只适用于 where name=。。。。。不适合 like关键字。

<h2>Unique Key</h2>

<div> 
    <p>
        单列唯一:
    </p>
	<code>create tabble department(
        id int,
        name char(8) unique
        );</code><br>
    <code>create table department(
    	id int,
        name char(8),
        unique(id),
        unique(name)
        );
    </code>
    <p>
        联合唯一:
    </p>
    <code>create table services(
    	id int,
        ip char(15),
        port int,
        unique(ip,port)
        );
    </code>
</div>

<address>定义地址</address>

<h2>primary key</h2>

<div> 
	<p>
        约束：not none unique
    </p>
    存储引擎（innodb）：对于innodb存储引擎来说，一张表内必须有一个主键
    <p>
        单列主键:
    </p>
    <code>create table t7(
    	id int primary key,
      	name char(16)
        );
    </code><br>
    <p>
        复合主键：
    </p>
    <code>create table t8(
    	ip char(15),
        port int,
        primary key(ip,port)
        );
    </code>
</div>

<h2>auto_increment</h2>

<div>
	<code>create table t9(id int primary key auto_increment);</code><br>
    <code>insert into t9(name) values('Wang'),('cheng'),('gong'),('yuan');</code><br>
    <code>insert into t9(id,name) values(8,'Cheng');</code>
    <p>
        了解：
    </p>
    <code>show variables like 'auto_inc%';</code><br>
   	<P>
        步长：auto_increment_increment 默认为1<br>
        起始偏移量：auto_increment_offset 默认为1
    </P>
    <p>
        设置步长：
        <code>set session auto_increment_increment=5;(局部有效，仅在该次有作用）</code><br>
        <code>set global auto_increment_increment=5;(全局有效)</code>
    </p>
    <p>
        设置偏移量：
        <code>set global auto_increment_offset=3;</code><br>
        <b>强调：起始偏移量<=步长</b><br>
    </p>
    <p>
        清空表：
        <code>delect from t3;</code><br>
        <em>这条语句通常与where语句联合使用。例如<code>delect from t3 where id=3;</code></em><br>
        这种清空方式并不会清空auto_increment_increment自增长的数据，下次插入数据会从当前的数据继续往下面插入。<br>
        清空表一般使用:<code>truncate t3;</code>
    </p>
</div>

<h2>foreign key</h2>

<div>
	<p>
        foreign key:用于建立表之间的关系。
    </p>
    <p>
        先建被关联的表(并保证被关联字段的唯一性）：<code>create table dep(id int primary key,name char(16),comment char(50));</code>
    </p>
    <p>
        在建立关联的表：
    <code>create table emp(id int primary key,name char(10),sex enum('male','famale'),dep_id int,foreign key(dep_id) references dep(id) on delete cascade(#删除同步) on update cascade(#更新同步));</code>
   </p>
    <p>
        <b>插入数据</b>
        先往被关联表插入数据：<br>
        <code>insert into dep values(1,'IT','技术派'),(2,'销售','买东西'),(3,'财务','发钱');</code><br>
        再往关联表中插入数据:<br>
        <code>insert into emp values(1,'Cheng','male','1');</code>
    </p>
</div>

<h2>数据的增删改查</h2>

<div>
    <p>
        插入查询结果：
    </p>
    <code>insert into 表名(字段1，地段2，字段3......) select (字段1，字段2，字段3.......) from 表名 where......;</code>
    <p>
        更新数据（update）：
        <code>update 表名 set 字段1=值1，字段2=值2，where condition....;</code>
    </p>
    <p>
        删除数据：<code>delete from 表名 where Condition;</code>
    </p>
    <p>
        查询数据：
    </p>
    <div>
       	<p>
            单表查询：
        </p>
        select distinct（防止重复)字段1，字段2，字段3...from 库.表 where 条件 group by 分组条件 having 过滤 order by 排序字段 limit n（选取n条数据）；
    </div>
    <div>
        <p>
            简单查询：
        </p>
        <p>
            聚合函数：max，min，avg，sum，count;<br>
            使用group by是首先要设置mysql格式，mysql>set global sql_mode="ONLY_FULL_GROUP_BY";<br>
            分组之后只能取分组的字段，以及每个组聚合的结果。
        </p>
        <p>
            每个部门有多少员工:<br>
            <code>select post,count(id) as emp_count from employee group by post;</code>
        </p>
        没有Group by则默认整体算作一组，可以使用聚合函数。<br>
        select max(salary) from employee;<br>
        <p>
            #group_concat:
            <code>select post,group_concat(name) from employee group by post; </code>
        </p>
        <p>
            having：这种过滤实在分组之后的。<br>
            查询各岗位内包含的员工个数小于2的岗位名、岗位内包含员工名字、个数。<br>
            <code>select post,group_concat(name),count(id) from employee group by post having count(id) < 2;</code>
        </p>
        <P>
            order by:<br>
            select * from employee order by age asc;(其中asc代表升序，dsc是降序排列)。<br>
            select * from employee order by age asc,id desc;(先按照age升序排列，如果age相同则按照id降序排列。)<br>
            select distinct post,count(id) as emp_count from employee where salary > 1000 group by post
having count(id) > 1 order by emp_count desc;            </P>
            order by emp_count 可以使用emp_count元素，说明order by是在最后运行的语句。
        <P>
            <b>limit语法：</b>
            select * from employee limit 0,5;（从0开始取五条数据）
            </P>
            <em><b>总结</b></em>
            <code>select distinct 字段1，字段2，字段3 from 库.表 where 条件 group by 分组条件 having 过滤 order by 排序字段 limit n；</code><br>
            执行循序：找表>where condition>group by>having>distinct>order by>limit>结束
    </div>
</div>

<div>
	<p>
        <b>正则查询：</b>
        <code>select * from employee where name like 'jin%';</code><br>
        <code>select * from employee where name regexp '^jin';(查找jin开头的用户)</code><br>
        <code>select * from employee where name regexp '^jin.*(g|n)$';（以jin开头以g或n结尾的用户）</code><br>
    </p>
</div>


​    

<h2>多表查询</h2>

<div>
	<p>
        <b>内连接：</b>只取两张表的共同部分。<code>select * from employee inner join department on employee.dep_id = department.id;</code>
    </p>
    <p>
        <b>左连接：</b>在内连接的基础上保留左表的记录。<code>select * from employee left join department on employee.dep_id = department.id;</code>
    </p>
     <p>
        <b>右连接：</b>在内连接的基础上保留右表的记录。<code>select * from employee right join department on employee.dep_id = department.id;</code>
    </p>
    <p>
        <b>全外连接：</b>在内连接的基础上保留左右表的记录。<code>select * from employee left join department on employee.dep_id = department.id union select * from employee right join department on employee.dep_id = department.id;</code>
    </p>
    <p>
        查询平均年龄大于30岁的部门名：<code>select department.name,avg(age) from employee inner join department on employee.dep_id = department.id group by department.name having avg(age) > 30;</code>
    </p>
    <p>
        <b>带IN关键字的子查询：</b><br>
        （查询平均年龄在25岁以上的部门名）：<code>select name from department where id in (select dep_id from employee group by dep_id having avg(age)>25);</code><br>
        （查询技术部门员工姓名）：<code>select name from employee where dep_id = (select id from department where name="技术");</code><br>
        （查看不足1人的部门名）：<code>select name from department where id not in(select distinct dep_id from employee);</code>
        <b>带比较运算符的子查询：</b><br>
        （查询大于所有人平均年龄的员工名与年龄）：<code>select name,age from employee where age >(select avg(age) from employee);</code><br>
        <em><b style="color:red;font-size:20px">SQL不允许在where子句中直接使用聚合函数（sum，avg等）</b></em><br>
    </p>
    <p>
        <b>带EXISTS关键字的子查询：</b><br>
        <code>select * from employee where EXISTS (select id from department where name = "IT");</code>
    </p>
    (每个部门最新入职的那名员工)：<code>select * from employee as t1 inner join (select post,max(hire_date) as max_hire_date from employee group by post) as t2 on t1.post = t2.post where t1.hire_date = max_hire_date;</code>
</div>
<h2>权限管理</h2>

<div>
	<p>
        1.创建账号：<br>
        #本地帐号：<code>create user 'Cheng'@'loaclhost' identified by '123'; (#mysql -uCheng -p123)</code><br>
        #远程账号:<code>create user 'Cheng'@'192.168.31.2' identified by '123'; (#mysql -uCheng -p123 -h 服务端ip)</code><br>
        :<code>create user 'Cheng'@'192.168.31.%' identified by '123'; (#mysql -uCheng -p123 -h 服务端ip)</code><br>
        :<code>create user 'Cheng'@'%' identified by '123'; (#mysql -uCheng -p123 -h 服务端ip)</code><br>
    </p>
    <p>
        2.授权：<br>
        user:*.*<br>
        db:db1.*<br>
        tables_priv:db1.t1<br>
        columns_priv:id,name<br>
        grant all on *.* to 'Cheng'@'localhost';(给所有权限)<br>
        grant select on *.* to 'Cheng'@'localhost';(只给select权限)<br>
        revoke select on *.* from 'Cheng'@'localhost';<br>
        grant select on db1.* to 'Cheng'@'localhost';(只对db1数据库下的表授权select权限)<br>
        revoke select on db1.* from 'Cheng'@'localhost';(删除权限)<br>
        grant select on db1.t1 to  'Cheng'@'localhost';(只授权表db1.t1 select权限);<br>
        revoke select on db1.t1 from 'Cheng'@'localhost';(删除权限)<br>
        grant select(id,name),update(age) on db1.t2 to 'Cheng'@'localhost';<br>

    </p>
</div>

<h2>Navicat工具的使用</h2>

<h3>pymysql模块的使用</h3>

```
#pip install pymysql
import pymysql
user = input("user>> ").strip()
pwd = input("pwd>> ").strip()
# 1:建立连接
conn=pymysql.connect(
    host='localhost',
    port=3306,
    user='root',
    passwd='',
    charset='utf8'
)
#拿到游标
cursor=conn.cursor()
#执行sql语句
sql = 'select * from db7.user_info where user="%s" and pwd="%s"' % (user,pwd)
rows = cursor.execute(sql)
cursor.close()
conn.close()
if rows:
    print("登陆成功")
else:
    print('登陆失败')
```

<h3>增删改</h3>

<code></code>

```
# import pymysql
# #建立连接>>拿到游标>>执行sql语句>>关闭游标>>关闭链接
# conn = pymysql.connect(
#     host='localhost',
#     port=3306,
#     user='root',
#     passwd='',
#     db='db7',
#     charset='utf8'
# )
# cur = conn.cursor()
# #增、删、改
# sql='insert into user_info(user,pwd) values (%s,%s)'
#print(cur.lastrowid) #显示插完数据后，最后一条数据id数值
# rows = cur.execute(sql,('Mei','222'))
# print(rows)
# conn.commit()
# cur.close()
# conn.close()
```

<h3>查</h3>

<code></code>

```
import pymysql
conn = pymysql.connect(
    host='localhost',
    port=3306,
    user='root',
    passwd='',
    db='db7',
    charset='utf8'
)
cur = conn.cursor(pymysql.cursors.DictCursor)
cur.execute('select * from user_info')
# print(cur.fetchone()) #取一条数据
# print(cur.fetchmany(2)) #指定去两条
print(cur.fetchall())
'''fatchall（）运行一次以后再次运行fatchall（）后返回结果为NULL，涉及到cursor的操作.
(1)cursor.scroll(3,mode='absolute') #相对绝对位置移动 即从位置0开始往后移动
(2)cursor.scroll(3,mode='relative') #相对当前位置移动 光标从当前位置进行移动
'''
conn.commit()
cur.close()
conn.close()
```

<h2>MySql内置功能介绍</h2>

<div>
	<p>
        视图：<code>create view course2teacher as select * from course inner join teacher on course.teacher_id=teacher.tid;</code>
    </p>
    视图是用来查的，不是用来改的。<br>
    <p>
        触发器:<code>create table cmd(
        id int primary key auto_increment,
        user char(32),
        priv char(10),
        cmd char(64),
        sub_time datetime,
        success enum('yes','no')
        );
        </code><br>
        <code>create table errlog(
        	id int primary key auto_increment,
            err_cmd char(64),
            err_time datetime);
        </code><br>
        <code>delimiter //
        	create trigger tri_after_inser_cmd after insert on cmd for each row 
          	begin 
            	if new.success='no' then
            		insert into errlog (err_cmd,err_time) values (new.cmd,new.sub_time);
            	end if;
            end//
            delimiter ;
        </code>
    </p>
    <code>show create trigger tri_after_inser_cmd;</code><br>
    <code>drop  trigger tri_after_inser_cmd;</code>
    <p>
        存储过程(无参)：<code>delimiter //
        	create procedure p1()
        	begin
        		select * from db7.teacher;
        	end//
        	delimiter ;
        </code><br>
        <code> show create procedure p1;</code><br>
        #1，在Mysql中调用：<code>call p1();</code><br>
        #2,在Python中调用：<code>cursor.callproc('p1');</code>
    </p>
     <p>
        存储过程(有参)：<code> 
        </code><br>
        <code>delimiter //
            create procedure p2(in n1 int,in n2 int,out res int,inout n3 int)
         	begin
            select * from db7.teacher where tid>n1 and tid < n2;
            set res=1;
            end//
            delimiter ;
         </code><br>
        #1，在Mysql中调用：<code>set @x=1; call p2(2,4,@x); select @x;</code><br>
        #2,在Python中调用：<code>cursor.callproc('p2',(2,4,0)) (@_p2_0=2,@_p2_1=4,@_p2_2=0)
            					cursor.execute('select @_p2_2')
            					cursor.fetchone()
            </code>
    </p>
</div>

<h2>应用程序与数据库结合使用</h2>

<div>
	<p>
        方式一：<br>
        Python:调用存储过程；<br>
        MySql:编写存储过程;<br>
        方式二；<br>
        Python:编写纯生SQL;<br>
        MySql：不用干;<br<
        方式三：<br>
        Python:ORM->纯生SQL<br>
        MySql:
    </p>
</div>

<h2>事务</h2>

<div>
	<p>
        事务用于将某些操作的多个SQL作为原子性操作，一旦有某一个出现错误，即可回滚到原来的状态，从而保证数据库数据的完整性。
    </p>
    <code>create table user (id int primary key auto_increment,name char(32),balance int);<br>
    	insert into user(name,balance) values('wsa',1000),('wang',2000),('cheng',90000);<br>
        #原子操作：<br>
        start transaction;
        update user set balance=900 where name='wsa';转账100<br>
        update user set balance=2010 where name='wang';中介拿走10<br>
        update user set balance=90090 where name='cheng';卖家拿到90，出现异常没有拿到。<br>
        rollback;
        commit;
    </code>
</div>
select (select course_id,max(num) as first_num from score group by course_id) as t1 inner join (select course_id,max(num) as second_num from score inner join (select course_id,max(num) as first_num from score group by course_id) as t on score.course_id=t.course_id where score.num < t.first_num group by course_id) as t2 on t1.course_id=t2.course_id) as t3 

<h2>Redis</h2>

<div>
	<p>
        Redis：（远程字典服务）是一个使用ANSI C编写的开源、支持网络、基于内存、可选持久性的键值对存储数据库，是NoSQL数据库。
    </p>
    Redis特性：<br>
    <ul>
        <li>速度快</li>
       	<li>持久化</li>
        <li>多种数据结构</li>
        <li>支持多种编程语言</li>
        <li>主从复制</li>
        <li>高可用、分布式</li>
    </ul>
    <p>
        redis的字符串操作：redis可以理解为一个全局的大字典，key就是数据的唯一标识。根据Key对应的值不同，可以划分为五个基本数据类型:<br>
    <ol>
        <li>String类型：字符串类型，是redis中最基础的数据存储类型，他在redis中是二进制安全的，也就是byte类型。单个数据的最大容量为512M。Key：值<br>
        相关操作:<code>set key value; get key;del key;</code>
        </li>
        <li>数组（list）：数组的成员数据类型为String。<code>lpush key value1 value2.....;<br>rpush key value1 value2 value3<br> lrange names 0 -1;（取出list中所有的数据）;<br>linsert key after|before 指定元素value<br>基于索引获取列表成员：lindex names 3<br>llen names(查询列表有多少个元素)。<br>删除指定成员：lpop key(第一个成员出列) rpop key(最后一个成员出列)    #这两个操作都是获取并删除的操作。<br> lrem key count value    #count表示删除的数量，value标识要删除的成员，该命令默认表示将列表从左侧前count个value的元素移除。<br>#count==0，表示删除列表中所有值为value的成员。<br>#count>0,表示删除列表左侧开始的前count个value成员。<br>#count<0, 表示删除列表右侧开始的前count个value成员。</code></li>
        <li>hash类型：字符串类型，用于存储对象/字典，对象/字典的结构为键值对。key、域、值的类型都为String。域在用一个hash中是唯一的。<br>key:{
            域（属性）：值，<br>域：值，<br>.....}     <br> 设置指定键的属性/域<code>hset key field1 value1 field 2 value2...</code><br>#取hash中某个字段的值<code>hget info name <br>#获取所有hash值：hgetall info <br>#获取多个值：hmget info field1 field2....<br>#删除指定键的域/属性:hdel key field1 field2...<br>#判断指定属性/域是否存在于当前键对应的hash中. hexists info age <br>#属性值自增自减：hincrby key field number <br>#获取所有键：hkeys info<br>#获取所有值:hvals info<br></code>
                </li>
    <li>集合（set）#无序集合，重点就是无序、去重：<br>#添加元素<code>sadd key member1 member2...例：sadd author wang cheng li zhang<br>#获取所有成员:smembers key 例：smembers author <br>#获取集合的长度：scard key 例：scard author <br>#随机抽取一个或多个元素：spop key [count=1]  (count为可选参数，默认为1，被提取的成员会在集合中删除掉)<br>#删除指定元素：srem key value 例：删除author中的cheng   srem author cheng<br>#交、差、并集：sinter key1 key2 key3 #交集，比较多个集合中共同存在的成员；sdiff key1 key2 key3 #差集，比较多个集合中不同的成员；sunion key1 key2 key3 #并集，合并所有集合的成员，并去重；</code></li>
    <li>zset（有序集合）：有序集合，去重并根据score权重值来进行排序，score从小到大进行排序。<br>#添加成员；zadd key score1 key1 score2 key2 score3 key3....<br>#获取score在指定区间的所有成员；zrangebyscore key min max # 按照score进行从低到高排序获取指定score区间<br>zrevrangebyscore key max min #按照score进行从高到低排序获取指定score区间<br>zrange key start stop #按照score进行从低到高进行排序获取指定索引区间<br>zrevrange key start stop (start,stop为切片索引）#按照score进行从高到低排序并获取指定索引区间<br>#获取集合长度：zcard key<br>#获取指定成员的权重值：zscore key member<br>#获取指定成员在集合中的排名：zrank key member (排名从0开始) #score从小到大进行排序;zrevrank key member #score从大到小进行排序<br>#获取score在指定区间的所有成员数量：zcount key min max<br>#给指定成员增加权重：zincrby key score member<br>#删除成员：zrem key member1 member2 member3<br>#删除指定数量的成员；（1）删除指定数量的成员，从低score开始删除；zpopmin key [count]<br>#删除指定数量的成员，从最高score开始删除 zpopmax key [count]</li>
</ol>    
</p>
</div>
<h2>Python操作redis</h2>

<div>
	<p>
        Python操作redis数据类型:
    </p>
    <code>import redis
poor = redis.ConnectionPool(host='localhost', port=6379, db=0)
r = redis.Redis(connection_pool=poor)
# #(1)字符串操作
# r.set('key1', 'value1')
# print(r.get('key1'))
# #不允许对已经存在的键进行操作
# ret = r.setnx('Cheng', '23')
# print(ret) #False
# #字符串操作，对键值设置有效期
# r.setex('Cheng', '50', 23)
# #自增自减
# r.set('Cheng',23)
# r.incrby('Cheng',1)
# r.decrby('Cheng',1)
# print(r.get('Cheng'))
#(2)Hash操作
# r.hset('info','name','Cheng')
# print(r.hget('info','name'))
# r.hmset('info',{'gender':'male','age':23})
# print(r.hgetall("info"))
#(3)list操作
# r.delete('score')
# r.rpush('score',100,90,80)
# r.rpush('score',70)
# r.lpush('score',120)
# print(r.lrange('score',0,-1))
# r.linsert('score','after',100,95)
# print(r.lpop('score'))
# print(r.rpop('score'))
# print(r.lindex('score',1))
#(4)集合操作
#key对应的集合中添加元素
# r.delete('name_set')
# r.sadd('name_set','zhangsan','lisi','wangwu')
# #获取key对应集合的所有成员
# print(r.smembers('name_set'))
# #从key对应的集合中随机获取numbers个元素
# print(r.srandmember('name_set',2))#只有查询操作不会进行删除
# r.srem('name_set','wangwu')
# print(r.smembers('name_set'))
#(5)有序集合操作
#在key对应的有序集合中添加元素
r.zadd('jifengbang',{'cheng':100,'wang':90,'li':80,'zhao':70})
#按照所应范围获取key对应的有序集合的元素
print(r.zrange('jifengbang',0,-1))
print(r.zrange('jifengbang',0,-1,withscores=True))
print(r.zrevrange('jifengbang',0,-1,withscores=True))
print(r.zrangebyscore('jifengbang',0,100))
print(r.zrangebyscore('jifengbang',0,100,start=0,num=1)) #start=0,num=1 相当于在0，100这个范围删选完后
#再从索引为0的地方开始，选取一个元素
#删除key对应的有序集合中值是value的成员
print(r.zrem('jifengbang','cheng'))#删除成功返回1
print(r.zrange('jifengbang',0,-1))
#键操作
r.delete('score')
print(r.exists('score'))
print(r.keys("*"))
r.expire('name',10)#设置过期时间</code>





<h3>延迟队列</h3>

<code></code>

```
import redis
import uuid
import time
import threading
pool=redis.ConnectionPool(host='127.0.0.1',port=6379,db=3,decode_responses=True)
r=redis.Redis(connection_pool=pool)
def delyTask(name,delyTime):
    task_id = str(uuid.uuid4())
    processTime = time.time() + delyTime
    r.zadd('delay-queue',{name+task_id:processTime})
def loop():
    while 1:
        task_list=r.zrangebyscore('delay-queue',0,time.time(),0,1)
        if not task_list:
            print("cost 1 second")
            time.sleep(1)
            continue
        task_id = task_list[0]
        ok = r.zrem('delay-queue',task_id)
        if ok:
            handleTask(task_id)
def handleTask(task_id):
    print(f"任务{task_id}执行完毕")
t = threading.Thread(target=loop)
t.start()
delyTask('任务一',5)
delyTask('任务二',2)
delyTask('任务三',4)
delyTask('任务四',10)
```

    <h2>关于发布订阅的聊天室案例</h2>

<div>
	subscribe key（监听键）<br>
    publish key message（发布者发布消息后监听键可以听到消息）<br>
    <p>
        生产端：<br><code>import redis
r = redis.Redis(host='localhost', port=6379, db=0)
r.publish('room_101','welcome to room_101')</code>
    </p>
    <p>
        消费端：<br><code>import redis
r = redis.Redis(host='localhost', port=6379, db=0)
pub=r.pubsub()
pub.subscribe('room_101')
pub.parse_response()
while True:
    print('waiting')
    res_mes = pub.parse_response()
    print(f"msg:{res_mes}")</code>
    </p>
    <p>
        基于redis的发布订阅的聊天室案例：<br><code>import redis
import threading
r = redis.Redis(host='localhost', port=6379, db=0)
def send_msg():
    msg=input('>>>')
    r.publish('room_1001',msg)
def recv_msg():
    pub = r.pubsub()
    pub.subscribe('room_1001')
    pub.parse_response()
    while 1:
        res_msg=pub.parse_response()
        print('>>>',res_msg)
t = threading.Thread(target=send_msg)
t.start()
recv_msg()</code>
    </p>
</div>       

 <h2>Python 内存管理、垃圾回收</h2>

<div>	
	<p>
        引用计数器为主，标记清除和分代回收为辅+缓存机制。
    </p>
    <h3>
        引用计数器：<br>
    </h3>
    1.1环状双向链表refchain：在python中创建的任何对象都会放在双向链表中。
</div>

name='cheng'   #内部会创建一些数据（上一个对象、下一个对象、类型、引用个数）

age=18  #内部会创建一些数据（上一个对象、下一个对象、类型、引用个数、val=18）

hobby=['篮球','美女'] #内部会创建一些数据（上一个对象、下一个对象、类型、引用个数、items=元素、元素个数）

在C源码中如何体现每个对象中都有的相同的值：PyObject结构体（4个值）。

有多的元素组成的对象：PyObject结构体（4个值）+ob_size。

1.2 类型封装结构体

data=3.14  #内部会创建 _ob_next(指向refchain中的下一个对象)	_ob_prev（指向refchain中的上一个对象）	_ob_refcnt=1	_ob_type=float	_ob_fval=3.14

1.3 引用计数器

v1=3.14

v2=999

v3=（1，2，3）

当python程序运行时会根据数据类型不同，会找到对应的结构体，会根据结构体中的字段来创建相关的数据，然后将对象添加到refchain双向链表中。

在源码中，有两个关键的结构体，PyObject，PyVarObject

每个对象中都有ob_refcnt就是引用计数器，默认为1，当有其他变量对这个对象进行引用时，引用计数器就会发生变化。

#引用

a=9999

b=1  #则a中的引用计数器就会加1

#当引用计数器为0时，意味着没有人使用这个对象了，这个对象就是垃圾，垃圾回收。

#回收：1、将对象从refchain链表中进行移除；2、将对象销毁、内存归还

1.4 循环引用（交叉感染）问题

![image-20250708162404376](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250708162404376.png)

2 标记清除

#为了解决引用计数器循环引用的不足。

实现：在Python的底层再去维护一个链表，链表中专门放可能存在循环引用的对象（list、tuple、dict、set）。

![image-20250708164257315](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250708164257315.png)

在python内部，某种情况下触发，会去扫描可能存在循环应用的链表的每个元素，检查是否有循环引用，如果有则让双方的引用计数器-1；如果是0则垃圾回收。

#什么时候扫描一次？

#可能存在循环引用的链表扫描代价大，每次扫描耗时久。

3 分代回收

 ![image-20250708165223302](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250708165223302.png)

将可能存在循环引用的对象维护成3个链表：

0代：0代中对象个数到达700个扫描一次。

1代：0代扫描十次，则一代扫描一次。

2代：1代扫描十次，则二代扫描一次。

4.小节

在Python中维护了一个refchain双向环状链表，这个链表中存储程序创建的所有对象，每中类型的对象中都有一个ob_refcnt引用计数器的值，引用个数+1，-1，最后当引用计数器变为0时会进行垃圾回收（对象销毁、refchain中移除）。

但是在Python中对于那些有多个元素组成的对象可能会存在循环引用的问题，为了解决这个问题，Python又引入了标记、清除、分代回收。在其内部维护了四个链表，refchain、2代、1代、0代。

在源码内部，当达到各自的阈值时就会触发扫描链表进行标记清除的动作（有循环各自减一）。

但是，Python源码内部在上述流程中提出了优化机制。

5 Python缓存

 5.1 池（int）

为了避免重复的创建，销毁常见对象，维护池。

#启动解释器时，Python内部帮我们创建：-5，-4，-3..........256

v1=7     #内部不会开辟内存，直接去池中获取

v2=9     #内部不会开辟内存，直接去池中获取

5.2 free_list（float，list，tuple，dict）

当一个对象的引用计数器为0时，按理说应该会收，但是内部不会直接回收，而是将对象添加到free_list中链表中当缓存。以后再去创建对象时，不再重新开辟内存，而是直接使用free_list。

v1=3.14 #开辟内存，内部存储结构体中定义那几个值，并存到refchain中。

del v1    #refchain中移除，将对象添加到free_list中。有个数限制。缓冲池满了才回去销毁。

v9=99.99 #不会重新开辟内存，取free_list中获取对象，对象内部数据初始化，再放到refchain中。

<h2>Selery</h2>

selery的架构由三部分组成，消息中间件，任务执行单元，任务执行结果存储组成。

![image-20250709110226694](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250709110226694.png)

![image-20250708233445729](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250708233445729.png)

Selery的异步执行语句：(消费者)

```
import celery
import time
backed='redis://localhost:6379/1'   #celery的结果会存储在redis中
broker='redis://localhost:6379/2'   #消息中简介
cel = celery.Celery('test', broker=broker, backend=backed)
@cel.task
def send_email(name):
    print('向%s发送邮件....'%name)
    time.sleep(4)
    print('向%s发送邮件完成'%name)
    return 'OK'
```

异步任务文件命令执行：

<code>celery -A  celery_task worker -l info</code>

celery_task.py

<code></code>

```
import celery
import time
backed='redis://127.0.0.1:6379/1'   #celery的结果会存储在redis中
broker='redis://127.0.0.1:6379/0'   #消息中简介
cel = celery.Celery('test', broker=broker, backend=backed)
@cel.task
def send_email(name):
    print('向%s发送邮件....' % name)
    time.sleep(4)
    print('向%s发送邮件完成' % name)
    return 'OK'
@cel.task
def msg(name):
    print('向%s发送信息....' % name)
    time.sleep(4)
    print('向%s发送信息完成' % name)
    return 'OK'
```

produce_task.py

<code></code>

```
from celery_task import send_email, msg
result = send_email.delay('Cheng')
print(result.id)
result = msg.delay('Cheng')
print(result.id)
```

result.py

<code></code>

```
from celery.result import AsyncResult
from celery_task import cel
async_result=AsyncResult(id='5420eae4-a085-4437-b266-020c0de23f08', app=cel)
if async_result.successful():
    print(async_result.get())
elif async_result.failed():
    print('任务执行失败')
elif async_result.state=='PENDING':
    print('任务等待中被执行')
elif async_result.state=='RETRY':
    print('任务异常后正在重试')
elif async_result.state=='STARTED':
    print('任务已经开始执行了')
```

2.1 多任务结构

目录结构

![image-20250709145624109](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250709145624109.png)

1）celery.py

<code></code>

```
from celery import Celery
cel = Celery('celery_demo',
             broker='redis://127.0.0.1:6379/1',
             backend='redis://127.0.0.1:6379/2',
             #包含以下两个任务文件，去相应的py文件中找任务，对多个任务做分类
             include=['celery_task.task01','celery_task.task02'])
#时区
cel.conf.timezone = 'Asia/Shanghai'
#是否使用UTC
cel.conf.enable_utc = False
```

2)task01

<code></code>

```
#task01
import time
from celery_task.celery import cel
@cel.task
def send_email(res):
    time.sleep(5)
    print('完成向%s发送邮件任务' % res)
    return "邮件发送完成！"
```

3)task02

<code></code>

```
#task02
import time
from celery_task.celery import cel
@cel.task
def send_msg(name):
    print('完成向%s发送短信任务' % name)
    time.sleep(5)
    return "短信发送完成！"
```

4)produce_task

<code></code>

```
from celery_task.task01 import send_email
from celery_task.task02 import send_msg
#立即告诉celery去执行tesk_celery任务，并传入一个参数
result = send_email.delay('Cheng')
print(result.id)
result = send_msg.delay('Wang')
print(result.id)
```

5）check_result

<code></code>

```
from celery.result import AsyncResult
from celery_task.celery import cel
async_result=AsyncResult(id='59fdcf86-cc53-4aab-a07a-3513faf21da5', app=cel)
if async_result.successful():
    print(async_result.get())
elif async_result.failed():
    print('任务执行失败')
elif async_result.state=='PENDING':
    print('任务等待中被执行')
elif async_result.state=='RETRY':
    print('任务异常后正在重试')
elif async_result.state=='STARTED':
    print('任务已经开始执行了')
```

2.2 celery执行定时任务

该过程celery_task包不变

produce_task:

<code></code>

```
# from celery_task.task01 import send_email
# from celery_task.task02 import send_msg
# #立即告诉celery去执行tesk_celery任务，并传入一个参数
# result = send_email.delay('Cheng')
# print(result.id)
# result = send_msg.delay('Wang')
# print(result.id)


##定时任务执行
from celery_task.task01 import send_email
from datetime import datetime
from datetime import timedelta
#方式一
# v1 = datetime(2025,7,9,16,0,0)
# print(v1)
# v2 = datetime.utcfromtimestamp(v1.timestamp())
# print(v2)
# result = send_email.apply_async(args=['Cheng'], eta=v2)
# print(result.id)
#方式二
ctime=datetime.now()
#默认用utc时间
utc_ctime = datetime.utcfromtimestamp(ctime.timestamp())
timedelay = timedelta(seconds=10)
task_time = utc_ctime + timedelay
#使用apply_async并设定时间
result = send_email.apply_async(args=['Wang'], eta=task_time)
print(result.id)
```

2.3 celery多目录结构下的定时任务

celery -A celery_task worker -l info -c 10  (其中-c 10 是选择并发个数)

1）首先在终端执行 celery -A celery_tesk worker -l info (开启监听redis中的消息队列)

2）执行celery -A celery_task beat (这条指令会根据celery_task包下的celery文件中的配置文件内容，定时向消息队列中放入任务。)

celery.py

<code></code>

```
from datetime import timedelta
from celery import Celery
cel = Celery('celery_demo',
             broker='redis://127.0.0.1:6379/1',
             backend='redis://127.0.0.1:6379/2',
             #包含以下两个任务文件，去相应的py文件中找任务，对多个任务做分类
             include=['celery_task.task01','celery_task.task02'])
#时区
cel.conf.timezone = 'Asia/Shanghai'
#是否使用UTC
cel.conf.enable_utc = False
#字典中每个键值对就是一个定时任务
cel.conf.beat_schedule = {
    #键时定时任务的名称
    'add_every_10_seconds':{
        #执行task1下的test_celery函数
        'task': 'celery_task.task01.send_email',
        'schedule': timedelta(seconds=6),
        'args': ('程',)
    },
}
```

<h2>Python asyncio高新能异步编程
</h2>

1）协程

实现协程的几种方法：

greenlet,早期模块

yield关键字

asyncio装饰器

async，await关键字

1.1 greenlet实现协程

<code>from greenlet import greenlet

​		def fun1():

​			print(1)

​			gr2.switch()

​			print(2)

​			gr2.switch()

​		def fun2():

​			print(3)

​			gr1.switch()

​			print(4)

​		gr1 = greenlet(fun1)

​		gr2 = greenlet(fun2)

</code>

1.2 yield 关键字

<code>

def func1() :

​	yield 1

​	yield from func2()

​	yield 2

def func2():

​	yield 3

​	yield 4

f1 = func1()

for item in f1:

​	print(item)

</code>

1.3 async

在python3.4以及之后版本

<code>

```Python
import asyncio
@asyncio.coroutine
def func1():
    print(1)
    yield from asyncio.sleep(2) #遇到IO耗时操作时，自动化切换到tasks中的其他任务
    print(2)
@asyncio.coroutine
def func2():
    print(3)
    yield from asyncio.sleep(2) #遇到耗时IO操作时，自动切换到tasks中的其他任务
    print(4)
tasks=[
    asyncio.ensure_future(func1()),
    asyncio.ensure_future(func2())
]
loop = asyncio.get_event_loop()
loop.run_until_complete(asyncio.wait(tasks))
```

</code>

1.4 asyncio & await 关键字

在python3.5及以后版本可用

<code></code>

```python
import asyncio

async def func1():
    print(1)
   await asyncio.sleep(2) #遇到IO耗时操作时，自动化切换到tasks中的其他任务
    print(2)

async def func2():
    print(3)
    await asyncio.sleep(2) #遇到耗时IO操作时，自动切换到tasks中的其他任务
    print(4)
tasks=[
    asyncio.ensure_future(func1()),
    asyncio.ensure_future(func2())
]
loop = asyncio.get_event_loop()
loop.run_until_complete(asyncio.wait(tasks))
```

2 协程的意义

在一个线程中如果遇到IO等待时间，县城不会傻傻的等待，利用空闲的时间去干点别的任务。

3 异步编程

3.1 事件循环

理解为一个死循环，去执行并检测某些代码。

![image-20250710154615594](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250710154615594.png)

<code>import asyncio #去获取一个事件循环    loop = asyncio.get_event_loop()    # 将任务放到任务列表中	loop.run_until_complete(任务)</code>

3.2 快速上手

写成函数：定于函数时 async def 函数名。

写成对象：执行写成函数（）得到的就是协程对象。

async def func():

​	pass

result = func()

#注意：执行写成函数创建写成对象后，函数内部代码不会执行。

<div>
	<code>
    	import async
        async def func():
        	print('1')
        result = func()
        loop = asyncio.get_event_loop()
        loop.run_until_complete(result)
    </code><br>
    如果想要运行写成函数内部代码，必须要将写协程对象交给事件循环来处理。<br>
    在python3.7之后，loop = asyncio.get_event_loop()
        loop.run_until_complete(result)   这两行代码直接使用 asyncio.run(result) 就可以运行了
</div>

<h2>Python中包引用问题
</h2>
3.3 await关键字

await + 可等待的对象（1，协程对象，Future，Task对象    IO等待）

示例一：

```python
import asyncio
async def func():
    print('hello world')
    result = await asyncio.sleep(2)
    print('结束',result)
asyncio.run(func())
```

示例二：

```python
import asyncio
async def others():
    print('start')
    await asyncio.sleep(2)
    print('end')
    return '返回值'
async def func():
    print('执行协程函数内部代码')
    #遇到IO操作挂起当前协程（任务），等IO操作完成后再继续往下执行。当前协程挂起时，事件循环可以去执行其他协程（任务）。
    response = await others()
    print('IO请求结束，结果为：',response)
asyncio.run(func())
```

示例三：

```python
import asyncio
async def others():
    print('start')
    await asyncio.sleep(2)
    print('end')
    return '返回值'
async def func():
    print('执行协程函数内部代码')
    #遇到IO操作挂起当前协程（任务），等IO操作完成后再继续往下执行。当前协程挂起时，事件循环可以去执行其他协程（任务）。
    response1 = await others()
    print('IO请求结束，结果为：',response1)
    response2 = await others()
    print('IO请求结束，结果为：', response2)
asyncio.run(func())
```

await就是等待对象的值得到结果之后再继续往下走。

3.4 Task 对象

在事件循环中添加多个任务。

Task用于并发调度协程，通过asyncio.create_task(协程对象)的方式创建Task对象，这样可以让协程加入事件循环中等待被调度执行。除了使用asyncio.create_task()函数外，还可以用低级的loop.create_task()或者ensure_future()函数，不建议手动实例化Task对象。

示例一：

```python
import asyncio
async def func():
    print(1)
    await asyncio.sleep(1)
    print(2)
    return "返回值"
async def main():
    print("main开始")
    #创建Task对象，将当前执行func函数任务 添加到事件循环中
    task1 = asyncio.create_task(func())
    #创建Task对象，将当前执行func函数任务添加到事件循环中
    task2 = asyncio.create_task(func())
    print("main结束")
    #当执行某协程遇到IO操作时，会自动化切换到其他任务
    #此处的awai是等待相应的协程全部运行结束并获取结果
    ret = await task1
    ret1 = await task2
    print(ret,ret1)
asyncio.run(main())
```

示例二：

```python
import asyncio
async def func():
    print(1)
    await asyncio.sleep(1)
    print(2)
    return "返回值"
async def main():
    print("main函数开始")
    task_list=[
        asyncio.create_task(func(),name='t1'),
        asyncio.create_task(func(),name='t2')
    ]
    print('main结束')
    done,pending = await asyncio.wait(task_list,timeout=None)
    print(done)
asyncio.run(main())
```

示例三：

```python
import asyncio
async def func():
    print(1)
    await asyncio.sleep(1)
    print(2)
    return '返回值'
task_list = [
    func(),
    func()
]
done,pending = asyncio.run(asyncio.wait(task_list))
print(done)
```

3.5 asyncio.future对象

Task继承Future，Task内部await结果的处理基于Future对象来的。

示例一：

```python
async def main():
    #获取当前循环事件
    loop = asyncio.get_running_loop()
    #创建一个（future对象），这个任务什么都不干
    fut = loop.create_future()
    #等待任务最终结果（future对象），没有结果则会一直等待下去。
    await fut
asyncio.run(main())
```

示例二：

```python
import asyncio
async def set_after(fut):
    await asyncio.sleep(2)
    fut.set_result("666")
async def main():
    #获取当前时间循环
    loop = asyncio.get_running_loop()
    #创建一个任务（future对象），没有绑定任何行为，则则会个任务永远不知道什么时候结束
    fut = loop.create_future()
    #创建一个任务（Task任务对象），绑定set_after函数，函数内部会在两秒之后给fut赋值。
    #及手动设置future任务的最终结果，否则一直等待下去
    await loop.create_task(set_after(fut))
    #等待fut对象获取最终结果，否则一直等待下去
    data = await fut
    print(data)
asyncio.run(main())
```

3.5 concurrent.futures.Future对象

使用线程池进程池实现异步操作时使用到的对象。

![image-20250712174851970](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250712174851970.png)

以后写代码可能存在交叉使用。例如：crm项目的80%都是基于协程异步编程+Mysql（如果MySql不支持）【则使用线程、进程做异步编程）。

示例：

![image-20250712175701714](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250712175701714.png)

案例：asyncio + 不支持异步的模块

![image-20250712175940721](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250712175940721.png)

3.6 asyncio异步迭代器

![image-20250714152930750](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250714152930750.png)

3.8 asyncio异步上下文管理器

![image-20250714153541939](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250714153541939.png)

4 uvloop

是asyncio的事件循环替代方案。事件循环效率>默认asyncio事件循环。

```python
pip install uvloop
```

```python
import asyncio 
import uvloop
async.set_event_loop_policy(uvloop.EventLoopPolicy())
#编写asyncio的代码与之前一样
#内部事件循环会自动变为uvloop
asyncio.run(...)
```

注意：一个asgi->uvicorn内部使用的就是uvloop

5 实战案例

在使用python代码使用redis时，链接/操作/断开都是网络IO

```python
pip install aioredis
```

示例1：

```python
import asyncio
import aioredis
async def execute(address,password):
    print('开始执行',address)
    #网络IO操作：创建redis连接
    redis = await aioredis.create_redis(address,password=password)
    #网络IO操作：再redis中设置哈希值car，内部再设置三个键值对，即：redis={car：{key1：1，key2：2，key3：3}}
    await redis.hmset_dict('car',key1=1,key2=2,key3=3)
    #网络IO操作：去redis中获取值
    result = await redis.hgetall('car',encoding='utf-8')
    print(result)
    redis.close()
    #网络IO操作关闭redis连接
    await redis.wait_closed()
    print('结束',address)
    asyncio.run(execute('redis://127.0.0.1:6379,'root!123'))
```

示例二：

![image-20250714160329391](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250714160329391.png)

5.2 异步MySQL

![image-20250714162144888](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250714162144888.png)

![image-20250714162343045](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250714162343045.png)

5.3  FastAPI框架

安装相应的包

```python
pip install fastapi
```

```python
pip install uvicorn(asgi内部基于uvloop)
```

5.4 爬虫

![image-20250714171022392](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250714171022392.png)
