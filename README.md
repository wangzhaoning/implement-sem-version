# Maven：实现语义化版本

之所以要出这么一道题，是因为很多年前我在面试时被要求手写一个[语义化版本](https://semver.org/lang/zh-CN/)的实现。

请实现一个[`Version`](https://github.com/hcsp/implement-sem-version/blob/master/src/main/java/com/github/hcsp/maven/Version.java)。注意，在这个过程中，你可以在两种方法中二选一：

- 自己造一个轮子。
- 或者通过自己的搜索，使用别人已经造好的轮子（语义化版本这样通用的东西肯定有人实现过），但是要把别人的轮子适配到我们要求的[`Version`](https://github.com/hcsp/implement-sem-version/blob/master/src/main/java/com/github/hcsp/maven/Version.java)中。

在提交Pull Request之前，你应当在本地确保所有代码已经编译通过，并且通过了测试(`mvn clean test`)

-----
注意！我们只允许你修改以下文件，对其他文件的修改会被拒绝：
- [src/main/java/com/github/hcsp/maven/Version.java](https://github.com/hcsp/implement-sem-version/blob/master/src/main/java/com/github/hcsp/maven/Version.java)
- [pom.xml](https://github.com/hcsp/implement-sem-version/blob/master/pom.xml)
-----


完成题目有困难？不妨来看看[写代码啦的相应课程](https://xiedaimala.com/tasks/661cd7ab-7fea-47d0-8e11-555d6fca751d)吧！

回到[写代码啦的题目](https://xiedaimala.com/tasks/661cd7ab-7fea-47d0-8e11-555d6fca751d/quizzes/6c87ef57-7f06-4af2-9112-86dd27ff099d)，继续挑战！
