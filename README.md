## Spring Boot Demo Application that integrates with Consul and Vault


### How to run?


### Dependencies:

	- Mysql
	- Docker
	- Consul
	- Vault


### Open Mysql

```
mysql -u root -p
Enter password:
```

```
mysql> GRANT ALL ON *.* to root@'xx.xx.xx.xx' IDENTIFIED BY 'root' WITH GRANT OPTION;

mysql> FLUSH PRIVILEGES;

mysql> create database demo;
```


### Run Consul
```
> docker run -d -p 8500:8500 -e CONSUL_BIND_INTERFACE=eth0 consul
```

### Open Consul UI:

http://localhost:8500/ui


### Create key:

config/java-app-demo/hello/name


### Download Vault

https://www.vaultproject.io/intro/getting-started/install.html


### Run Vault

```
> vault server -dev
```

### Run commands with vault:

```
> vault token-create

> vault secrets enable mysql

> vault write mysql/config/connection connection_url="root:root@tcp(127.0.0.1:3306)/"

> vault write mysql/roles/admin \
    sql="CREATE USER '{{name}}'@'%' IDENTIFIED BY '{{password}}';GRANT ALL ON *.* TO '{{name}}'@'%' WITH GRANT OPTION;"
```


### Get created token:
```
> vault token lookup
```

### Change key 'token' on resources/bootstrap.yaml
```
vault:
  host: ...
  port: ...
  uri: ...
  token: <PUT-VAULT-TOKEN-HERE>
```


### Compile and build Docker image:

```
> ./mvnw clean install

> docker build . -t java-app-demo

```

### Run app:

Set values for env variables on `run.sh.sample` file

And run:

```
> mv run.sh.sample run.sh

> chmod +x run.sh

> ./run.sh

```

### Insert data on user table:

```
> mysql -uroot -p
Enter password:

mysql> use demo;

mysql> CREATE TABLE user (
    ->   id INT NOT NULL AUTO_INCREMENT,
    ->   name VARCHAR(255),
    ->   email VARCHAR(255),
    ->   PRIMARY KEY (id),
    ->   UNIQUE INDEX (email)
    -> ) ENGINE=INNODB;

mysql> insert into user (name, email) values("DEMO USER", "demo@rivendel.com.br");
```