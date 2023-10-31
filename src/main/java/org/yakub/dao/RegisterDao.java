package org.yakub.dao;

import org.yakub.dto.RegisterDto;
import org.yakub.exception.NotFound404Exception;
import org.yakub.roles.ERoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class RegisterDao implements IDaoGenerics<RegisterDto>{
    /*
       Transaction
       connection.setAutoCommit(false); // default:true
       connection.commit(); // başarılı
       connection.rollback(); // başarısız
       */
    @Override
    public String speedData(Long id) {
    for (int i=1;i<=id;i++){
        try (Connection connection=getInterfaceConnection()){
            // Manipulation: executeUpdate() [create, delete, update]
            // Sorgularda  : executeQuery [list, find]
            // Transaction:
            connection.setAutoCommit(false);//default true
            String sql="INSERT INTO public.register (nick_name,email_address,password,roles,remaining_number,is_passive) \n"+
                    "VALUES (?,?,?,?,?,?)";
            String rnd= UUID.randomUUID().toString();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, "nickname() "+rnd);
            preparedStatement.setString(2, "emailAddress()"+rnd);
            preparedStatement.setString(3, "password()"+rnd);
            preparedStatement.setString(4, ERoles.USER.getValue());
            preparedStatement.setInt(5, 5);
            preparedStatement.setBoolean(6, true);
            // executeUpdate: create, delete, update için kullanılır.
            int rowsEffected = preparedStatement.executeUpdate();
            // eğer ekleme yapılmamışsa -1 değerini döner
            if (rowsEffected > 0) {
                System.out.println(RegisterDao.class + " Başarılı Ekleme Tamamdır");
                connection.commit(); // başarılı
            } else {
                System.err.println(RegisterDao.class + " !!! Başarısız Ekleme Tamamdır");
                connection.rollback(); // başarısız
            }
        }catch (SQLException sql){
            sql.printStackTrace();
        }catch (Exception e){

        }
    }
        System.out.println( id+ "tane veri eklendi");
        return id+ "tane veri eklendi";
    }

    @Override
    public String allDelete() {
        try (Connection connection=getInterfaceConnection()){
            // Manipulation: executeUpdate() [create, delete, update]
            // Sorgularda  : executeQuery [list, find]
            // Transaction:
            connection.setAutoCommit(false);
            String sql="DELETE FROM public.register";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            // executeUpdate: create, delete, update için kullanılır.
            int rowsEffected = preparedStatement.executeUpdate();
            // eğer silme yapılmamışsa -1 değerini döner
            if (rowsEffected > 0) {
                System.out.println(RegisterDao.class + " Başarılı Bütün Veriler Silme Tamamdır");
                connection.commit(); // başarılı
            } else {
                System.err.println(RegisterDao.class + " !!! Başarısız Bütün Silme Tamamdır");
                connection.rollback(); // başarısız
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list().size()+" tane veri silindi";
    }
    ////////////////////////////////////////////////////////
    @Override
    public RegisterDto create(RegisterDto registerDto) {
        try(Connection connection=getInterfaceConnection()){
            // transaction: ya hep ya hiç kuralıdır.
            connection.setAutoCommit(false); // default:true
             String sql="INSERT INTO public.register (nickname,email_address,password,roles,remaining_number,is_passive) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,registerDto.getuNickName());
            preparedStatement.setString(2,registerDto.getuEmailAddress());
            preparedStatement.setString(3,registerDto.getuPassword());
            preparedStatement.setString(4,registerDto.getRolles());
            preparedStatement.setInt(5,registerDto.getRemainingNumber());
            preparedStatement.setBoolean(6,registerDto.getPassive());
            // Ekleme yapmak
            // rowEffected=-1 eklenmemiştir.
            Integer rowseffected=preparedStatement.executeUpdate();
            if(rowseffected > 0){
                System.out.println(RegisterDao.class + "Ekleme Tamamlandı.");
                connection.commit();
            }else {
                System.err.println(RegisterDao.class + "Ekleme yapilamadi.");
                connection.rollback();
            }
            return  registerDto; // eğer başarılı ise return registerDt
        }catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception e){
          e.printStackTrace();
        }
        return  null;
    }//end create
    // FIND BY ID
    @Override
    public RegisterDto findById(Long id) {
        RegisterDto registerDto=null;
        try (Connection connection=getInterfaceConnection()){
            // Dikkat: id Long olduğu için tırnak içinde yazmıyoruz  örneğin: id=1
            String sql="select * from public.register where id="+id;
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            // executeUpdate() [create, delete, update]
            // Sorgularda  : executeQuery [list, find]
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                registerDto.setId(resultSet.getLong("id"));
                registerDto.setuNickName(resultSet.getString("nickname"));
                registerDto.setuEmailAddress(resultSet.getString("email_address"));
                registerDto.setuPassword(resultSet.getString("password"));
                registerDto.setRolles(resultSet.getString("roles"));
                registerDto.setRemainingNumber(resultSet.getInt("remaining_number"));
                registerDto.setPassive(resultSet.getBoolean("is_passive"));
                registerDto.setSystemCreatedDate(resultSet.getDate("system_created_date"));
            }
            return  registerDto; // eğer başarılı ise return registerDto
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // FIND EMAIL
    @Override
    public RegisterDto findByEmail(String email) {
        RegisterDto registerDto=null;
        try (Connection connection=getInterfaceConnection()) {
            // Dikkat: email_address String olduğu için tırnak içinde yazıyoruz örneğin: email="hamitmizrak@gmail.com"
            String sql="SELECT * FROM public.register where email_address='"+email+"\'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // executeUpdate() [create, delete, update]
            // Sorgularda  : executeQuery [list, find]
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                // nick_name, email_address, password, roles, remaining_number, is_passive
                registerDto=new RegisterDto();
                registerDto.setId(resultSet.getLong("id"));
                registerDto.setuNickName(resultSet.getString("nick_name"));
                registerDto.setuEmailAddress(resultSet.getString("email_address"));
                registerDto.setuPassword(resultSet.getString("password"));
                registerDto.setRolles(resultSet.getString("roles"));
                registerDto.setRemainingNumber(resultSet.getInt("remaining_number"));
                registerDto.setPassive(resultSet.getBoolean("is_passive"));
                registerDto.setSystemCreatedDate(resultSet.getDate("system_created_date"));
            }
            return  registerDto; // eğer başarılı ise return registerDto
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //LIST
    @Override
    public ArrayList<RegisterDto> list() {
        ArrayList<RegisterDto>list=new ArrayList<>();
        RegisterDto registerDto;
        try (Connection connection=getInterfaceConnection()){
            String sql="select * from public.register";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            // executeUpdate() [create, delete, update]
            // Sorgularda  : executeQuery [list, find]
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                // eğer burada new(instance) yapmazsak sadece son veriiyi ekler
                registerDto=new RegisterDto();
                registerDto.setId(resultSet.getLong("id"));
                registerDto.setuNickName(resultSet.getString("nick_name"));
                registerDto.setuEmailAddress(resultSet.getString("email_address"));
                registerDto.setuPassword(resultSet.getString("password"));
                registerDto.setRolles(resultSet.getString("roles"));
                registerDto.setRemainingNumber(resultSet.getInt("remaining_number"));
                registerDto.setPassive(resultSet.getBoolean("is_passive"));
                registerDto.setSystemCreatedDate(resultSet.getDate("system_created_date"));
                list.add(registerDto);
            }
            /*list.forEach(System.out::println);*/
            return  list; // eğer başarılı ise return registerDto
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    // UPDATE
    // transaction: create,delete,update
    @Override
    public RegisterDto update(Long id, RegisterDto registerDto) {
        // İlgili ID nesne varsa update yapsın
        RegisterDto find=findById(id);
        if(find !=null){
            try(Connection connection= getInterfaceConnection()) {
                // transaction: ya hep ya hiç kuralıdır.
                connection.setAutoCommit(false);
                String sql="UPDATE public.register SET nick_name=?,email_adress=?,password=?,roles=?,remaining_number=?,is_passive=? WHERE id=?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,registerDto.getuNickName());
                preparedStatement.setString(2, registerDto.getuEmailAddress());
                preparedStatement.setString(3,registerDto.getuPassword());
                preparedStatement.setString(4,registerDto.getRolles());
                preparedStatement.setInt(5,registerDto.getRemainingNumber());
                preparedStatement.setBoolean(6,registerDto.getPassive());
                preparedStatement.setLong(7, registerDto.getId());
                // executeUpdate: create, delete, update için kullanılır.
// Ekleme yapmak
                Integer rowsEffected= preparedStatement.executeUpdate();
                // eğer güncelle yapılmamışsa -1 değerini döner
            if(rowsEffected>0){
                System.out.println(RegisterDao.class+"Güncelleme yapildi");
                connection.commit();
            }else {
                System.err.println(RegisterDao.class+"Güncelleme yapilamadi");
                connection.rollback();
            }
            return  registerDto;
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            System.err.println("Böyle Bir kullanıcı yoktur ve güncelleme yapılamaz...");
        }
        return null;
    }
    // UPDATE (REMAING NUMBER)
    @Override
    public RegisterDto updateRemaing(Long id, RegisterDto registerDto) {
        RegisterDto find=findById(id);
        if(find!=null){
            try (Connection connection=getInterfaceConnection()){
             connection.setAutoCommit(false);
             String sql="UPDATE public.register SET remaining_number =? WHERE id=?";
             PreparedStatement preparedStatement= connection.prepareStatement(sql);
             preparedStatement.setInt(1,registerDto.getRemainingNumber());
             preparedStatement.setLong(2,registerDto.getId());
                // executeUpdate: create, delete, update için kullanılır.
                int rowsEffected = preparedStatement.executeUpdate();
                // eğer güncelle yapılmamışsa -1 değerini döner
                if (rowsEffected > 0) {
                    System.out.println(RegisterDao.class + " Başarılı Kalan Hak Güncelleme Tamamdır");
                    connection.commit(); // başarılı
                } else {
                    System.err.println(RegisterDao.class + " !!! Başarısız Kalan Hak Güncelleme Tamamdır");
                    connection.rollback(); // başarısız
                }
                return registerDto; // eğer başarılı ise return registerDto
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            System.err.println("Böyle bir kullanıcı yoktur");
        }
        return null;
    }




    // DELETE
    // transaction: create,delete,update
    @Override
    public RegisterDto deleteById(RegisterDto registerDto) {

        RegisterDto registerFindbyId=findById(registerDto.getId());
        if (registerFindbyId !=null){
        try (Connection connection=getInterfaceConnection()){
          connection.setAutoCommit(false);
          String sql="DELETE FROM public.register WHERE id="+registerDto.getId();
          PreparedStatement preparedStatement=connection.prepareStatement(sql);
          preparedStatement.setLong(1,registerDto.getId());
            // Ekleme yapmak
            Integer rowsEffect = preparedStatement.executeUpdate();
            if (rowsEffect > 0) {
                System.out.println(RegisterDao.class + " Silme Başarılı");
                connection.commit();
            } else {
                System.err.println(RegisterDao.class + " Silme yapılmadı");
                connection.rollback();
            }
            return registerDto; // eğer başarılı ise return registerDto
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        }else {
            throw  new NotFound404Exception((registerDto.getId())+"Veri bulunamadı!!");
        }
        return null;
    }
}//end class RegisterTdo
