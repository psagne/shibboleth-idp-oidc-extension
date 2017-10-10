# shibboleth-idp-oidc-extension

[![Build Status](https://travis-ci.org/CSCfi/shibboleth-idp-oidc-extension.svg?branch=master)](https://travis-ci.org/CSCfi/shibboleth-idp-oidc-extension)

The goal of the project is to provide a [OpenID Connect](http://openid.net/connect/) OP extension to [Shibboleth IdP V3](https://wiki.shibboleth.net/confluence/display/IDP30/Home). The work is done as part of task T3.1A OpenID Connect Federation in GN4-2 JRA3 project.

The development has only gone through few development cycles and goal is to pilot it early 2018. 

This far the primary goal has been on understanding how [Nimbus library](https://connect2id.com/products/nimbus-oauth-openid-connect-sdk) should be integrated with [Shibboleth IdP V3](https://wiki.shibboleth.net/confluence/display/IDP30/Home) and no effort has yet been made on areas beyond that.

The Shibboleth IdP installed by this project is able to act as a *noncompliant* [OpenID Connect](http://openid.net/connect/).  


## Prerequisites
- Java 7+
- [Apache Maven 3](https://maven.apache.org/)
- [Vagrant](https://www.vagrantup.com/)

## Deployment
The maven project needs to be built first. The ansible scipts will then perform first installation of Shibboleth Idp V3, after which the extensions are installed. 

```

git clone https://github.com/CSCfi/shibboleth-idp-oidc-extension
cd shibboleth-idp-oidc-extension/idp-oidc-extension-parent/
mvn package
cd ..
vagrant up

```

## Playing around

### Login
You need to be root to access all the necessary files. 
```
vagrant ssh
sudo su -
```

### View logs
By following log entries it should be possible to get an idea of the execution.
```
tail -f /opt/shibboleth-idp/logs/idp-process.log
``` 

### Initiate authentication
There are few static links available in https://192.168.0.150/.
```
Authentication request
Authentication request: code flow (unsupported)
Authentication request: max_age=0 / forceAuthn
Authentication request: prompt=none / isPassive
Authentication request: wrong redirect uri
Authentication request: unknown client id
Authentication request, acr=password
Authentication request, acr=notsupported
```
Please note the page itself is not informative of the outcome, all the action needs to be followed from the log file. 

#### LDAP User to authenticate with
The LDAP user is Ted Tester, in Finnish:

```
user:teppo
password:testaaja
```

### Configuration
See wiki

