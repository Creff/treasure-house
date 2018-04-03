package com.pfs.ip.datamodel.temptest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.PrivilegedExceptionAction;
import java.sql.*;

public class App {

    private static final String JDBC_URL = "jdbc:impala://host:21050;"
            + "SSL=1;"
            + "CAIssuedCertNamesMismatch=1;"
            + "SSLTrustStore=C:/Program Files/workenvironment/jdk1.7.0_51_64bit/jre/lib/security/cacerts;"
            + "SSLTrustStorePwd=changeit;"
            + "AuthMech=1;"
            + "KrbServiceName=impala;"
            + "KrbAuthType=1;"
            + "KrbRealm=xxx.COM;"
            + "KrbHostFQDN=host";

    private static UserGroupInformation ugi;
    
    public void show() throws Exception {
        try {
            System.out.println("Principal Authentication: ");
            System.out.println(JDBC_URL);
            /** *************Kerberos Only Start**************** **/
            final String user = "username@xxx.COM";
            final String keyPath = "C:/Program Files/workenvironment/jdk1.7.0_51_64bit/jre/lib/security/pamuser.keytab";


            Configuration conf = new Configuration();
//            conf.set("hadoop.security.authentication", "kerberos");
            conf.set("hadoop.rpc.protection", "privacy");
//            conf.set("dfs.namenode.kerberos.principal", "hdfs/_HOST@CURNX.COM");

            UserGroupInformation.setConfiguration(conf);
            System.out.println(user+" "+keyPath);
//            UserGroupInformation.loginUserFromKeytab(user, keyPath);
//            System.out.println("login finished");
            
            this.ugi = UserGroupInformation.loginUserFromKeytabAndReturnUGI(user, keyPath);
            System.out.println("Kerberos Only End");
            /** ******************************* **/


            System.out.format("Entered %-30s %-20s  \n", "JdbcTest", "003");
            try {
                Class.forName("com.cloudera.impala.jdbc41.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            }
            
             
//            Connection con = (Connection) this.ugi.doAs((PrivilegedExceptionAction<Object>) () -> {
//                Connection c = null;
//                try {
//                    c = DriverManager.getConnection(JDBC_URL);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                return c;
//            });

            Connection tc = null;
            try {
            	tc = DriverManager.getConnection(JDBC_URL);
            } catch (SQLException e) {
            	e.printStackTrace();
            }
            
//            Connection con = (Connection) this.ugi.doAs((PrivilegedExceptionAction<Object>)tc);
            
            try {
                Statement stmt = tc.createStatement();
                String sql = "select distinct asof from metldw595d.bv_trade_f";
                System.out.format("Entered %-30s %-20s  \n", "JdbcTest", "005.1");
                ResultSet res = stmt.executeQuery(sql);
                if (!res.isBeforeFirst()) {
                    System.out.format("Entered %-30s %-20s  \n", "No Rows Found", "005");
                } else {
                    if (res.next()) {
                        for (int i = 1; i < res.getMetaData().getColumnCount() + 1; i++) {
                            System.out.print(" " + res.getMetaData().getColumnName(i) + "=" + res.getObject(i) + "\n");
                        }
                    }
                }
                System.out.format("Entered %-30s %-20s  \n", "JdbcTest", "006");
            } catch (Exception e) {
                throw new Exception("Failed to execute JDBC " + e.getMessage());
            }

        } catch (Exception e) {
            throw new Exception("Failed to Execute -- xxx --- " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
//        System.setProperty("java.security.krb5.conf", "C:/Program Files/workenvironment/jdk1.7.0_51_64bit/jre/lib/security/krb5.conf");//Kerberos Only

        App testHandler = new App();
        try {
            testHandler.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

