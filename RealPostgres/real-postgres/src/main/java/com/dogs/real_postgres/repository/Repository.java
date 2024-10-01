package com.dogs.real_postgres.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

//import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

@org.springframework.stereotype.Repository
public class


Repository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    final TransactionTemplate transactionTemplate;

//    private EntityManager entityManager;

    @Autowired
    public Repository(
            JdbcTemplate jdbcTemplate,
            PlatformTransactionManager transactionManager
    ) {
       this.jdbcTemplate = jdbcTemplate;
       this.transactionTemplate = new TransactionTemplate(transactionManager);
       transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//       this.entityManager = entityManager;
    }


    public void createUser(
            String login,
            double longitude,
            double latitude
    ) {
        jdbcTemplate.update("insert into public.users (login, longitude, latitude) values (?, ?, ?)", login, longitude, latitude);
    }

    public User getUser(
            String login
    ) {
        return jdbcTemplate.queryForObject("select * from public.users where login = ?", User.rowMapper, login);
    }

    //changed this for vid 3
    public String createDog(
            String name,
            double longitude,
            double latitude
    ) {
        final String id = UUID.randomUUID().toString();
        jdbcTemplate.update("insert into public.dogs (id, name, longitude, latitude) values (?, ?, ?, ?)", id, name, longitude, latitude);
        return id;
    }

    public Dog getDog(
            String id
    ) {
        return jdbcTemplate.queryForObject("select * from public.dogs where id = ?", Dog.rowMapper , id);
    }

    public void createWalk(
            String userLogin,
            String dogName,
            double longitude,
            double latitude,
            Date startTime,
            long durationMinutes,
            double price,
            boolean isAccepted
    ) {
        jdbcTemplate.update("insert into public.walks (dog_id, user_login,  longitude, latitude, start_time, duration_minutes, price_usd, is_accepted) values (?, ?, ?, ?, ?, ?, ?, ?)",

                jdbcTemplate.queryForObject("select id from public.dogs where name = ?", String.class , dogName),
                userLogin,

                longitude,
                latitude,
                startTime,
                durationMinutes,
                price,
                isAccepted
        );
    }

    public Walk getWalk(
            String userLogin,
            String dogName,
            Date startTime
    ) {
        String dogId = jdbcTemplate.queryForObject("select id from public.dogs where name = ?", String.class , dogName);
        return jdbcTemplate.queryForObject("select * from public.walks where user_login = ? and dog_id = ? and start_time = ?", Walk.rowMapper , userLogin, dogId, startTime);
    }

    public void updateUserBalance(
            String login,
            double amount
    ) {
        jdbcTemplate.update("update public.users set balance = ? where login = ?", amount, login);
    }

    //part 4
    public List<Dog> getClosestDogs(
            double ln,
            double lat,
            int cnt
    ) {
        final String coords = String.format("'(%f, %f)'", ln, lat);
        final String selectClosestDogsSql = String.format("SELECT * FROM public.dogs ORDER BY location <-> point %s LIMIT %d", coords, cnt);
        return jdbcTemplate.query(selectClosestDogsSql, Dog.rowMapper);
    }

    public int countDogsByCoords(
            double ln,
            double lat
    ) {
        final Integer cntFromDb = jdbcTemplate.queryForObject("select count(*) from public.dogs where longitude = ? and latitude = ?", Integer.class, ln, lat);
        return cntFromDb != null ? cntFromDb : 0;
    }

    public void advisoryLockByCoords(
            double ln,
            double lat
    ) {
        final Long lockNum = (long)(ln * 10_000_000 + lat * 10_000);
        jdbcTemplate.queryForObject("SELECT pg_advisory_lock(?)", Object.class, lockNum);
    }

    public void advisoryUnlockByCoords(
            double ln,
            double lat
    ) {
        final Long lockNum = (long)(ln * 10_000_000 + lat * 10_000);
        jdbcTemplate.queryForObject("SELECT pg_advisory_unlock(?)", Object.class, lockNum);
    }

//    public void advLockEM(
//            double ln,
//            double lat
//    ) {
//        String queryString = "SELECT pg_advisory_lock(:lockNum)";
//        final Long lockNum = (long)(ln * 10_000_000 + lat * 10_000);
//        entityManager.createNativeQuery(queryString).setParameter("lockNum", lockNum).getSingleResult();
//    }

    public <T> T doInTransaction(Supplier<T> r) {
         return transactionTemplate.execute(
                (status) ->  r.get()
        );
    }

    public List<Map<String, Object>> getTop10WalkingUsersFromDb() {
       return jdbcTemplate. queryForList(
               "select user_login from walks group by user_login having count(*) >= 10 order by count(*) desc limit 10");
    }






}
