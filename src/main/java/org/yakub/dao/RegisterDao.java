package org.yakub.dao;

import org.yakub.dto.RegisterDto;
import org.yakub.exception.NotFound404Exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDao implements IDaoGenerics<RegisterDto>{
    /*
       Transaction
       connection.setAutoCommit(false); // default:true
       connection.commit(); // başarılı
       connection.rollback(); // başarısız
       */
    @Override
    public String speedData(Long id) {
        return null;
    }

    @Override
    public String allDelete() {
        return null;
    }

    @Override
    public RegisterDto create(RegisterDto registerDto) {
        try(Connection connection=getInterfaceConnection()){
            // transaction: ya hep ya hiç kuralıdır.
            connection.setAutoCommit(false); // default:true
             String sql="INSERT INTO public.register (nickname,email_address,password,remaining_number,is_passive) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,registerDto.getuNickName());
            preparedStatement.setString(2,registerDto.getuEmailAddress());
            preparedStatement.setString(3,registerDto.getuPassword());
            preparedStatement.setInt(4,registerDto.getRemainingNumber());
            preparedStatement.setBoolean(5,registerDto.getPassive());
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


        }catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception e){
          e.printStackTrace();
        }
        return  registerDto;
    }//end create
    // FIND BY ID
    @Override
    public RegisterDto findById(Long id) {
        RegisterDto registerDto=new RegisterDto();
        try (Connection connection=getInterfaceConnection()){
            String sql="select * from public.register where id="+id;
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                registerDto.setId(resultSet.getLong("id"));
                registerDto.setuNickName(resultSet.getString("nickname"));
                registerDto.setuEmailAddress(resultSet.getString("email_address"));
                registerDto.setuPassword(resultSet.getString("password"));
                registerDto.setRemainingNumber(resultSet.getInt("remaining_number"));
                registerDto.setPassive(resultSet.getBoolean("is_passive"));
                registerDto.setSystemCreatedDate(resultSet.getDate("system_created_date"));
            }
            System.out.println(registerDto);
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return registerDto;
    }
    //LIST
    @Override
    public ArrayList<RegisterDto> list() {
        ArrayList<RegisterDto>list=new ArrayList<>();
        RegisterDto registerDto;
        try (Connection connection=getInterfaceConnection()){
            String sql="select * from public.register";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                // eğer burada new(instance) yapmazsak sadece son veriiyi ekler
                registerDto=new RegisterDto();
                registerDto.setId(resultSet.getLong("id"));
                registerDto.setuNickName(resultSet.getString("nick_name"));
                registerDto.setuEmailAddress(resultSet.getString("email_address"));
                registerDto.setuPassword(resultSet.getString("password"));
                registerDto.setRemainingNumber(resultSet.getInt("remaining_number"));
                registerDto.setPassive(resultSet.getBoolean("is_passive"));
                registerDto.setSystemCreatedDate(resultSet.getDate("system_created_date"));
                list.add(registerDto);
            }
            list.forEach(System.out::println);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
    // UPDATE
    // transaction: create,delete,update
    @Override
    public RegisterDto update(Long id, RegisterDto registerDto) {
        // İlgili ID nesne varsa update yapsın
        RegisterDto registerbyfind=findById(id);
        if(registerbyfind !=null){
            try(Connection connection= getInterfaceConnection()) {
                // transaction: ya hep ya hiç kuralıdır.
                connection.setAutoCommit(false);
                String sql="UPDATE public.register SET nick_name=?,email_adress=?,password=?,remaining_number=?,is_passive=?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,registerDto.getuNickName());
                preparedStatement.setString(2, registerDto.getuEmailAddress());
                preparedStatement.setString(3,registerDto.getuPassword());
                preparedStatement.setInt(4,registerDto.getRemainingNumber());
                preparedStatement.setBoolean(5,registerDto.getPassive());
// Ekleme yapmak
                Integer rowsEffected= preparedStatement.executeUpdate();
            if(rowsEffected>0){
                System.out.println(RegisterDao.class+"Güncelleme yapildi");
                connection.commit();
            }else {
                System.err.println(RegisterDao.class+"Güncelleme yapilamadi");
                connection.rollback();
            }
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            throw new NotFound404Exception((id+"veri bulunamadı !!!"));
        }
        return registerDto;
    }
    // DELETE
    // transaction: create,delete,update
    @Override
    public RegisterDto delete(RegisterDto registerDto) {
        //hocaya neden findbyıd yaptiğimiz sorulucak!
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

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        }else {
            throw  new NotFound404Exception((registerDto.getId())+"Veri bulunamadı!!");
        }
        return registerDto;
    }
}//end class
