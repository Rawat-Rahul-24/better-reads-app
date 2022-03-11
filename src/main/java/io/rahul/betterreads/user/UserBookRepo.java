package io.rahul.betterreads.user;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserBookRepo extends CassandraRepository<UserBook, UserBookPrimaryKey> {

}
