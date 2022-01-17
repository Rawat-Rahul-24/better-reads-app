package io.rahul.betterreads.user;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BooksByUserRepo extends CassandraRepository<BooksByUser, String> {

    Slice<UserBook> findAllById(String id, Pageable pageable);
}