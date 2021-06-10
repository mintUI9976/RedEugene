[![Loading-RedEugene](https://image.sv-studios.net/88ba04b95d70d27e5c570c8ad54c89838.png "https://gitlab.zyonicsoftware.com")](https://gitlab.zyonicsoftware.com)
<div align="center">
  <h2>An simple job system based of ScheduledThreadPoolExecutor</h2>
  <hr />
  <a href="https://github.com/mintUI9976/RedEugene/releases"><img src="https://img.shields.io/github/v/release/mintUI9976/RedEugene?include_prereleases&color=red" /></a>
  <a href="https://github.com/mintUI9976/RedEugene"><img src="https://img.shields.io/github/languages/code-size/mintUI9976/RedEugene?color=orange" /></a>
  <a href="https://github.com/mintUI9976/RedEugene"><img src="https://img.shields.io/tokei/lines/github/mintUI9976/RedEugene?color=yellow" /></a>
  <a href="https://github.com/mintUI9976/RedEugene/blob/master/LICENSE"><img src="https://img.shields.io/github/license/mintUI9976/RedEugene" /></a>
  <a href="https://discord.gg/vFVk4TmNQC"><img src="https://img.shields.io/discord/743171495454441503?label=discord&color=cyan" /></a>
  <img src="https://img.shields.io/badge/opensource-❤-green">
  <br />
  <br />
  <a href="https://zyonicsoftware.com">Website</a>
  <span>&nbsp;&nbsp;•&nbsp;&nbsp;</span>
  <a href="https://github.com/mintUI9976/RedEugene/wiki">Wiki</a>
  <span>&nbsp;&nbsp;•&nbsp;&nbsp;</span>
  <a href="https://github.com/mintUI9976/RedEugene/blob/master/LICENSE">License</a>
  <span>&nbsp;&nbsp;•&nbsp;&nbsp;</span>
  <a href="https://gitlab.zyonicsoftware.com">Gitlab</a>
  <span>&nbsp;&nbsp;•&nbsp;&nbsp;</span>
  <a href="https://github.com/Zyonic-Software">Github</a>
  <span>&nbsp;&nbsp;•&nbsp;&nbsp;</span>
  <a href="https://twitter.com/zyonicsoftware">Twitter</a>
  <br />
  <hr />
</div>
<div align="left">
<h4>Support and Project Discussion:</h4>
</div>

[![Loading-Discord](https://seeklogo.com/images/D/discord-logo-855AEC93F1-seeklogo.com.png "https://discord.gg/vFVk4TmNQC")](https://discord.gg/vFVk4TmNQC)

<hr />
<div align="left">
<h4>Dependencies & Repository:</h4>
<h5>Maven:</h5>

````java
<repositories>
<repository>
<id>jitpack.io</id>
<url>https://jitpack.io</url>
</repository>
</repositories>
````

````java
<dependency>
<groupId>com.github.mintUI9976</groupId>
<artifactId>RedEugene</artifactId>
<version>version</version>
</dependency>
````

<h5>Gradle:</h5>

````java
maven{url'https://jitpack.io'}
````

````java
compile"com.github.mintUI9976:RedEugene:version"
        compile group:'com.github.mintUI9976',name:'RedEugene',version:'version'
````

<hr />
<h4>Prerequisites:</h4>

``JDK 11 or above``
<hr />
<h4>Features:</h4>

- none monitoring thread to check or work with your threads(tasks)
- simple implementation
- simple to use
- own simple job system
- custom thread factory
- custom exception handler
- simple runnable reference interface instance
- cached information about your jobs (live and canceled)
- jobs based of scheduler and void executor
- thread pool statistics

<hr />
<h4>Utilization:</h4>

````java
private static final RedEugene redEugene=new RedEugene(yourName,coreSize,deamon,priority);
private final RedEugeneIntroduction redEugeneIntroduction=new RedEugeneIntroduction(redEugene);
````

<hr />

<h4>Examples:</h4>

</div>


