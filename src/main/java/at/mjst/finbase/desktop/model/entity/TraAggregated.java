/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.model.entity;

import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import at.mjst.finbase.desktop.common.field.Field;

import static at.mjst.finbase.desktop.model.entity.Entity.SCHEMA_FINBASE;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-29
 */
@Entity
@Immutable // mark entity as readonly (hibernate)
@Table(name = TraAggregated.VIEW_TRA_AGGREGATED, schema = SCHEMA_FINBASE)
public class TraAggregated extends AbstractEntity
{
    public static final String VIEW_TRA_AGGREGATED = "tav_tra_aggregated";
    // Uncommon Fields
    protected static final String FIELD_BALANCE = "balance";
    protected static final String FIELD_BALANCE_POS = "balance_pos";
    protected static final String FIELD_BALANCE_NEG = "balance_neg";
    protected static final String FIELD_VALUE_DATE_FROM = "value_date_from";
    protected static final String FIELD_VALUE_DATE_TO = "value_date_to";
    protected static final String FIELD_TRANSACTION_COUNT = "transaction_count";
    // 'Field'-Objects
    private final Field<Integer> accId = Field.createInteger(FIELD_ID, this);
    private final Field<BigDecimal> balance = Field.createBigDecimal(FIELD_BALANCE, this);
    private final Field<BigDecimal> balance_pos = Field.createBigDecimal(FIELD_BALANCE_POS, this);
    private final Field<BigDecimal> balance_neg = Field.createBigDecimal(FIELD_BALANCE_NEG, this);
    private final Field<LocalDate> valueDateFrom = Field.createLocalDate(FIELD_VALUE_DATE_FROM, this);
    private final Field<LocalDate> valueDateTo = Field.createLocalDate(FIELD_VALUE_DATE_TO, this);
    private final Field<Long> transaction_count = Field.createLong(FIELD_TRANSACTION_COUNT, this);

    @Basic
    @Column(name = FIELD_BALANCE_POS, precision = 4, updatable = false)
    public BigDecimal getBalance_pos()
    {
        return balance_pos.getValue();
    }

    private void setBalance_pos(BigDecimal balance_pos)
    {
        this.balance_pos.setValue(balance_pos);
    }

    @Basic
    @Column(name = FIELD_BALANCE_NEG, precision = 4, updatable = false)
    public BigDecimal getBalance_neg()
    {
        return balance_neg.getValue();
    }

    private void setBalance_neg(BigDecimal balance_neg)
    {
        this.balance_neg.setValue(balance_neg);
    }

    @Basic
    @Column(name = FIELD_TRANSACTION_COUNT, nullable = false, updatable = false)
    public Long getTransaction_count()
    {
        return transaction_count.getValue();
    }

    private void setTransaction_count(Long transaction_count)
    {
        this.transaction_count.setValue(transaction_count);
    }

    @Id
    @Column(name = FIELD_ID, nullable = false, updatable = false)
    public Integer getAccId()
    {
        return accId.getValue();
    }

    private void setAccId(Integer id)
    {
        this.accId.setValue(id);
    }

    @Basic
    @Column(name = FIELD_BALANCE, precision = 4, nullable = false)
    public BigDecimal getBalance()
    {
        return balance.getValue();
    }

    private void setBalance(BigDecimal balance)
    {
        this.balance.setValue(balance); // ToDo: external set of value should insert a row into tra...!
    }

    @Basic
    @Column(name = FIELD_VALUE_DATE_FROM, updatable = false)
    public LocalDate getValueDateFrom()
    {
        return valueDateFrom.getValue();
    }

    private void setValueDateFrom(LocalDate valueDateFrom)
    {
        this.valueDateFrom.setValue(valueDateFrom);
    }

    @Basic
    @Column(name = FIELD_VALUE_DATE_TO, updatable = false)
    public LocalDate getValueDateTo()
    {
        return valueDateTo.getValue();
    }

    private void setValueDateTo(LocalDate valueDateTo)
    {
        this.valueDateTo.setValue(valueDateTo);
    }

    @Override
    public String tableName()
    {
        return VIEW_TRA_AGGREGATED;
    }

    @Override
    void buildBusinessKey(Collection<Field<?>> businessKey)
    {
    }
}
