/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kampusapp.dao;

;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kampusapp.model.DatabaseConfig;
import kampusapp.model.Mahasiswa;

/**
 *
 * @author acer
 */
public class kampusDAOProses implements KampusDAO {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement=null;
    PreparedStatement preparedStatement=null;
    @Override
    public List<Mahasiswa> get() {
        List<Mahasiswa> list=new ArrayList<Mahasiswa>();
        try{
            //list=new ArrayList<Mahasiswa>();
            String sql="Select * from tb_kas";
            connection=DatabaseConfig.openConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                Mahasiswa mhs=new Mahasiswa();
                mhs.setId(resultSet.getInt("id"));
                mhs.setNim(resultSet.getString("nim"));
                mhs.setNama(resultSet.getString("nama"));
                mhs.setBulan(resultSet.getString("bulan"));
                mhs.setBayar(resultSet.getString("bayar"));
                list.add(mhs);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Mahasiswa mahasiswa) {
        boolean flag=false;
        try{
            String sql="Insert Into tb_kas(nama,nim,bayar,bulan) values(?,?,?,?)";
            connection=DatabaseConfig.openConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, mahasiswa.getNama());
            preparedStatement.setString(2, mahasiswa.getNim());
            preparedStatement.setString(3, mahasiswa.getBayar());
            preparedStatement.setString(4, mahasiswa.getBulan());
            preparedStatement.executeUpdate();
            flag=true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public Mahasiswa getSinggle(int id) {
        Mahasiswa mhs=null;
        try{
            String sql="select * from tb_kas where id=?";
            connection=DatabaseConfig.openConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                mhs=new Mahasiswa();
                mhs.setId(resultSet.getInt("id"));
                mhs.setNim(resultSet.getString("nim"));
                mhs.setNama(resultSet.getString("nama"));
                mhs.setBayar(resultSet.getString("bayar"));
                mhs.setBulan(resultSet.getString("bulan"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return mhs;
    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        boolean flag=false;
        try{
            String sql="update tb_kas set nama=?,nim=?,bulan=?,bayar=? where id=?";
            connection=DatabaseConfig.openConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, mahasiswa.getNama());
            preparedStatement.setString(2, mahasiswa.getNim());
            preparedStatement.setString(3, mahasiswa.getBulan());
            preparedStatement.setString(4, mahasiswa.getBayar());
            preparedStatement.setInt(5, mahasiswa.getId());
            preparedStatement.executeUpdate();
            flag=true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        boolean flag=false;
        try{
           String sql="delete from tb_kas where id=?";
           connection=DatabaseConfig.openConnection();
           preparedStatement=connection.prepareStatement(sql);
           preparedStatement.setInt(1, id);
           preparedStatement.executeUpdate();
           flag=true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return flag;
    }
}
