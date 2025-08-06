<h2>Git指令</h2>

第一步：进入要管理的目录。

第二部：git init 初始化，即让git帮助我们管理当前文件夹

1 git status  检测当前目录下的文件状态

 	三种状态变化：

​		1）红色：新增文件/修改了老文件    通过git add 文件名  或者 git add .  将新增文件或者修改的老文件管理起来

​		2)绿色：git已经管理起来了，git commit -m '描述信息'   将管理起来的文件生成版本

2 git add 文件名  将文件添加到管理中

3 git add .   将剩余文件添加到管理中

4 git commit -m 'v1'  生成管理版本

5 git log 查看版本

6 git reset --hard 版本号     实现版本回滚（向前回滚）

7 向后回滚

```
git reflog
git reset --hard 版本号
```

![image-20250716151500087](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250716151500087.png)

![image-20250716161617883](C:\Users\86136\AppData\Roaming\Typora\typora-user-images\image-20250716161617883.png)

命令总结

查看分支

```
git branch
```

创建分支

```
git branch 分支名称
```

切换分支

```
git checkout 分支名称
```

分支合并（可能会产生冲突）

```
git merge 要合并的分支
注意：切换分支后再合并
```

删除分支

```
git branch -d 分支名称
```

git中使用SSL进行文件上传步骤

1 生成SSL密钥

在终端中执行以下命令，生成Github支持的ed25519类型密钥

```
ssh-keygen -t ed25519 -C "你的GitHub注册邮箱"
```

执行后会出现以下提示，按流程操作：

- `Enter file in which to save the key (/c/Users/你的用户名/.ssh/id_ed25519)`：直接按回车（使用默认路径）。
- `Enter passphrase (empty for no passphrase)`：可选密码（建议设置，增强安全性，后续使用密钥时需要输入）。
- `Enter same passphrase again`：重复输入密码（若第一步设置了）。

生成成功后，`.ssh` 目录会新增两个文件：

- `id_ed25519`：私钥（必须保密，不能泄露）。
- `id_ed25519.pub`：公钥（需要上传到 GitHub）。

2 查看并复制公钥内容

```
cat ~/.ssh/id_ed25519.pub
```

输出类似如下以 `ssh-ed25519` 开头，以你的邮箱结尾）：

```
ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAILQzxxxxxxxxxxxxxxxxxxxxxxx your_email@example.com
```

复制整行内容 

#### 3. 将公钥添加到 GitHub 账户

1. 登录 GitHub，点击右上角头像 → **Settings**（设置）。
2. 在左侧菜单找到 **SSH and GPG keys** → 点击 **New SSH key**（新建 SSH 密钥）。
3. 在 **Title** 中输入一个标识（如 “我的笔记本”），在 **Key** 中粘贴刚才复制的公钥内容。
4. 点击 **Add SSH key** 完成添加。

#### 4. 验证 SSH 连接是否成功

执行以下命令测试连接：

```
ssh -T git@github.com
```

- **成功提示**：出现类似 `Hi 你的GitHub用户名! You've successfully authenticated, but GitHub does not provide shell access.` 说明配置正确。
- **若提示输入 passphrase**：输入你生成密钥时设置的密码即可。

2.6 Github相关命令

给远程仓库起个别名（在家里上传代码）

```
1 git remote add xxx 远程仓库的http地址或者SSL地址
2 向远程仓库推送代码
git push -u xxx 分支
```

```
1 克隆远程仓库代码
git clone 远程仓库地址 （内部已经实现了git remote add origin 远程仓库地址）
2 切换分支
git checkout 分支
```

（到公司电脑上第一次获取代码）

在公司进行开发

```
1 切换到dev分支进行开发
	git checkout dev
2 把master分支合并到dev（仅一次）
	git merge master
3 修改代码
4 提交代码
	git add .
	git commit -m 'xx'
	git push origin dev
```

回到家中继续写代码

```
1 切换到dev进行开发
	git checkout dev
2 拉代码
	git pull origin dev
3 继续开发
4 提交代码
	git add .
	git commit -m 'xx'
	git push origin dev
```

回到公司继续开发

开发完毕要上线

```
1 将dev分支合并到master，进行上线
	git checkout master
	git merge dev
	git push origin master
2 将dev分支也推送到远程
	git checkout dev
	git merge master
	git push origin dev
```

```
git pull origin dev
等同于
git fetch origin dev + git merge origin/dev
```

快速解决冲突

1 安装beyond compare

2 在git中进行配置

```
git config --local merge.tool bc3
git config --local mergetool.path 'beyond compare的安装路径'
git config --local mergetool.keepBackup false
```

3 应用beyond compare解决冲突

```
git mergetool
```

总结

保持代码提交整洁（变基）

```
git rebase 分支
```

记录图形展示

```
git log --graph --pretty=format:"%h %s"
```

给开源软件贡献代码

1 fork源码

​	将别人的源码拷贝到自己的仓库中

2 在自己的仓库中修改代码

3 给源代码的作者提交 修复bug申请（pull request）

第三章 配置

项目配置文件：项目/.git/config

```
git config --local user.name 'Cheng'
git config --local user.email 'chengliyangg@qq.com'
```

全局配置文件：~/.gitconfig

```
git config --global user.name 'Cheng'
git config --global user.email 'chengliyangg@qq.com'
```

系统配置文件：/etc/.gitconfig

```
git config --system user.name 'Cheng'
git config --system user.email 'chengliyangg@qq.com'
注意需要有root权限
```

免密登录

URL中体现

```
原来的地址：https://github.con/Wupeiqi/dbhot.git
修改的地址：https://用户名:密码@github.com/Wupeiqi/dbhot.git

git remote add origin https://用户名:密码@github.com/Wupeiqi/dbhot.git
git push origin master
```

git 自动管理凭证

3.3 git忽略文件

让git不在管理当前目录下的某些文件

```
*.h    #以.h结尾的文件都不再管理
!a.h   #排除a.h文件，即继续管理a.h文件
files/ #files文件夹下的文件都不再在管理
*.py[a|b|c] #以.pya  .pyb .pyc 的文件都不再管理
```

更多参考:https://github.com/github/gitignore

3.4 GitHub任务管理相关

1 issues，文档以及任务管理

2 wiki，项目文档

创建标签示例

创建轻量标签和附注标签

```
git tag v1.0
git tag -a v1.1 -m "这是标签的内容"
```

查看标签和标签内容

```
git tag
git show v1.1
```

推送标签到远程仓库

```
git push origin v1.0
git push origin v1.1
git push origin --tags #推送所有标签
```

删除标签

```
本地删除
git tag -d v1.0
远程删除
git push origin --delete v1.0
```

标签的作用：

Git 中的标签（Tag）是用于标记代码仓库中特定提交点的引用，类似于 “书签” 功能。它们在软件开发中具有重要作用，主要用于以下场景：

1 版本管理：为软件的每个正式版本（如 v1.0.0、v2.3.1）创建标签，方便快速定位和回溯历史版本。

```
git tag v1.0.0  # 在当前提交创建标签
git tag v0.9.0 1f7ec59  # 在指定提交哈希创建标签
```

2 区分里程碑：标记重要的开发节点（如 Alpha/Beta 测试版本、重大功能发布），帮助团队和用户理解项目演进。

代码回溯与部署

**快速切换版本**

- 使用标签可以立即切换到历史版本，无需记忆复杂的提交哈希：

```
git checkout v1.0.0  # 切换到v1.0.0版本
```

版本说明

为标签添加注释（轻量标签 vs 附注标签），记录版本变更内容：

```
git tag -a v1.0.0 -m "首个正式版本，包含核心功能X、Y、Z"
```

1. **帮助新人理解项目**
   - 标签作为关键节点，让新成员快速了解项目的重要阶段和版本历史。

