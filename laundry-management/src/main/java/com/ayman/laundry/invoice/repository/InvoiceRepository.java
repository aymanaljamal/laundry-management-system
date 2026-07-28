package com.ayman.laundry.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.invoice.entity.Invoice;

import java.util.Optional;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);


    Optional<Invoice> findByOrderId(Long orderId);


    boolean existsByInvoiceNumber(String invoiceNumber);


}