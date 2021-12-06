CREATE DATABASE bbs;
USE bbs;
-- 帖子表
CREATE TABLE bbs_post(
  pid INT PRIMARY KEY AUTO_INCREMENT, -- 帖子编号
  pname VARCHAR(100) NOT NULL,        -- 帖子标题 
  pcontent VARCHAR(10000) NOT NULL,  -- 帖子内容
  ptime DATE NOT NULL,                -- 发布时间 
  uid INT,                            -- 用户编号
  FOREIGN KEY (uid) REFERENCES bbs_user(uid) ON UPDATE CASCADE ON DELETE CASCADE,
  cid INT,                            -- 类别编号
  FOREIGN KEY (cid) REFERENCES bbs_category(cid) ON UPDATE CASCADE ON DELETE CASCADE
);
-- 图片表
CREATE TABLE bbs_postimg(
 pgid INT PRIMARY KEY AUTO_INCREMENT, -- 图片编号
 pid INT NOT NULL,                    -- 帖子编号
 bigPic VARCHAR(1000) NOT NULL,       -- 大图地址
 smallPic VARCHAR(1000) NOT NULL,     -- 小图地址 
 FOREIGN KEY (pid) REFERENCES bbs_post(pid) ON UPDATE CASCADE ON DELETE CASCADE
);
-- 回复表
CREATE TABLE bbs_resp(
 rid INT PRIMARY KEY AUTO_INCREMENT,  -- 回复编号
 pid INT,                             -- 帖子编号
 FOREIGN KEY (pid) REFERENCES bbs_post(pid) ON UPDATE CASCADE ON DELETE CASCADE,
 uid INT,                             -- 用户编号
 FOREIGN KEY (uid) REFERENCES bbs_user(uid) ON UPDATE CASCADE ON DELETE CASCADE,
 rcontent VARCHAR(1000) NOT NULL      -- 回复内容	
);
-- 类别表
CREATE TABLE bbs_category(
 cid INT PRIMARY KEY AUTO_INCREMENT,  -- 类别编号
 cname VARCHAR(100) NOT NULL          -- 类别名称  
);
-- 管理员表
CREATE TABLE bbs_admin(
aid INT PRIMARY KEY AUTO_INCREMENT    -- 管理员编号
);
-- 用户表
CREATE TABLE bbs_user(
uid INT PRIMARY KEY AUTO_INCREMENT,   -- 用户编号
aid INT,
FOREIGN KEY (aid) REFERENCES bbs_admin(aid) ON UPDATE CASCADE ON DELETE CASCADE,
username VARCHAR(20) NOT NULL,        -- 用户名
PASSWORD VARCHAR(20) NOT NULL,        -- 密码
email VARCHAR(100) NOT NULL           -- 邮箱
);
-- 用户头像图片表
CREATE TABLE bbs_userimg(
 ugid INT PRIMARY KEY AUTO_INCREMENT, -- 图片编号
 uid INT NOT NULL UNIQUE,             -- 用户编号
 smallPic VARCHAR(1000) NOT NULL,     -- 头像地址 
 FOREIGN KEY (uid) REFERENCES bbs_user(uid) ON UPDATE CASCADE ON DELETE CASCADE
);
