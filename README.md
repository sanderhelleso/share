<h1 align="center">SHARE</h1>

<p align="center">
<b>A Spring boot filesharing service</b><br>
<sub>Share your files with anyone for free</sub>
</p>

<br>

<p align="center">
<a href="https://github.com/sanderhelleso">
<img src="https://github.com/sanderhelleso/share/blob/master/client/public/img/preview.gif" alt="preview">
</a>
</p>

<p align="center">
<img src="https://github.com/sanderhelleso/share/blob/master/client/public/img/demo3.jpg" alt="preview" height="600" width="600">
</p>

<br>
<br>

## ❯ Getting Started

You can get a local copy of the project by cloning the repo

```
git clone https://github.com/sanderhelleso/share.git
```
After cloning, cd into both client and server and start up the services respectively

### Notes 
- You must have mongoDB installed localy and running for data storage to work
- You need a valid API key from https://ipdata.co to track where users are dl files from

After recieving an api key, create a file called **config/Config.java** under the main share app folder.

```
package com.semanta.share.config;

public class Config {

    public final static String IPDATA_API_KEY = <API KEY>;
}
```


<br>

## ❯ LICENCE
MIT
