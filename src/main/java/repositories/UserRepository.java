//package repositories;
//
//import entities.User;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.List;
//
//public class UserRepository implements Repository<User>{
//
//    private Session session;
//
//    public UserRepository(Session session) {
//        this.session = session;
//    }
//
//    @Override
//    public void save(User user) {
//        Transaction trx = session.beginTransaction();
//        session.persist(user);
//        trx.commit();
//    }
//
//    @Override
//    public List<User> findAll() {
//        return null;
//    }
//
//    @Override
//    public User findOneById(long id) {
//        return null;
//    }
//
//    @Override
//    public void update(User user) {
//
//    }
//
//    @Override
//    public void delete(User user) {
//
//    }
//}
