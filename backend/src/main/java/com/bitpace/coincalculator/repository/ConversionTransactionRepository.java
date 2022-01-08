package com.bitpace.coincalculator.repository;

import com.bitpace.coincalculator.model.ConversionTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mert Cotuk
 */
public interface ConversionTransactionRepository extends JpaRepository<ConversionTransaction, Long> {
}
