# Networking-Lab
## NFS server configuration:  
We need to install NFS packages on NFS server, install it using the following command.  
  
[root@server ~]# yum install nfs-utils libnfsidmap  
Once the packages are installed, enable and start NFS services.  
  
systemctl enable rpcbind  
systemctl enable nfs-server  
systemctl start rpcbind  
systemctl start nfs-server  
systemctl start rpc-statd  
systemctl start nfs-idmapd  
  
Now, let’s create a directory to share with client servers. Here I will be creating a new directory named “nfsfileshare” in “/” partition.  
  
Note: You can also share your existing directory with NFS.  
  
[root@server ~]# mkdir /nfsfileshare  
Allow client servers to read and write to the created directory.  
  
[root@server ~]# chmod -R 777 /nfsfileshare/  
We have to modify “/etc/exports“file to make an entry of directory “/nfsfileshare” that you want to share.  
  
[root@server ~]# vi /etc/exports  
  
/nfsfileshare 192.168.1.0/24(rw,sync,no_root_squash)  
/nfsfileshare : shared directory  
  
rw : Writable permission to shared folder  
  
Export the shared directories using the following command.  
  
[root@server ~]# exportfs -r  
  
We need to configure firewall on NFS server to allow client servers to access NFS shares. To do that, run the following commands on the NFS server.  
  
firewall-cmd --permanent --zone public --add-service mountd  
firewall-cmd --permanent --zone public --add-service rpc-bind  
firewall-cmd --permanent --zone public --add-service nfs  
firewall-cmd --reload  
  
Configure NFS client  
We need to install NFS packages on NFS client-server to mount remote filesystem, install NFS packages using below command.  
  
[root@client ~]# yum -y install nfs-utils libnfsidmap  
Once the packages are installed, enable and start NFS services.  
  
systemctl enable rpcbind  
systemctl start rpcbind  
  
[root@client ~]# showmount -e 192.168.12.200  
  
Export list for 192.168.12.200:  
/nfsfileshare 192.168.1.201  
  
[root@client ~]# mkdir /mnt/nfsfileshare  
Use below command to mount a shared directory “/nfsfileshare” from NFS server “192.168.1.200” in “/mnt/nfsfileshare” on client-server.
  
[root@client ~]# mount 192.168.1.200:/nfsfileshare /mnt/nfsfileshare  

## SSH Server
sudo dnf install openssh-server  
  
sudo systemctl enable sshd  

sudo systemctl start sshd  
sudo systemctl status sshd  
  
Furthermore, you should now see the port 22 open for a new incoming connections:  

$ sudo ss -lt  

Now, we are ready to connect to the SSH server on the Fedora Workstation system. Example:  
$ ssh username@fedora-ip-or-hostname  
  
## Telnet Server  
sudo dnf install telnet telnet-server  
sudo systemctl start telnet.socket  
systemctl status telnet.socket  
telnet [hostname or IP address] [port]  
telnet example.com 23  
  
Secure telnet with firewall  
systemctl status firewalld  
sudo systemctl enable firewalld --now  
sudo firewall-cmd --permanent --add-service=telnet  
sudo firewall-cmd --permanent --add-service=telnet --add-source=192.168.1.100  
firewall-cmd --reload  
firewall-cmd --list-services  
  
## FTP server
sudo dnf install vsftpd  
vsftpd -v  
  
Open the /etc/vsftpd/vsftpd.conf file using the command below to edit the configurations using the nano editor.  

sudo nano /etc/vsftpd/vsftpd.conf  
  
listen=YES  
local_enable=YES  
xferlog_enable=YES  
connect_from_port_20=YES  
pam_service_name=vsftpd  
  
# Allow local user to upload files  
write_enable=YES  
  
# Enable Anonymous user to read files (no password, no username)  
anonymous_enable=YES  
anon_root=/var/ftp  
no_anon_password=YES  
EOF  

  
sudo firewall-cmd --add-service=ftp --permanent  
sudo firewall-cmd --reload  
  
sudo systemctl enable vsftpd  
sudo systemctl restart vsftpd  

ftp 192.168.1.47  
