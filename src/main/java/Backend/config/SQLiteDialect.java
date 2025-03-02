package Backend.config;

import org.springframework.data.relational.core.dialect.AbstractDialect;
import org.springframework.data.relational.core.dialect.LimitClause;
import org.springframework.data.relational.core.dialect.LockClause;
import org.springframework.data.relational.core.sql.LockOptions;

public class SQLiteDialect extends AbstractDialect {
    public static final SQLiteDialect INSTANCE = new SQLiteDialect();

    protected SQLiteDialect() {
        super();
    }

    @Override
    public LimitClause limit() {
        return new LimitClause() {
            @Override
            public String getLimit(long limit) {
                return String.format(" LIMIT %d", limit);
            }

            @Override
            public String getLimitOffset(long limit, long offset) {
                return String.format(" LIMIT %d OFFSET %d", limit, offset);
            }

            @Override
            public String getOffset(long offset) {
                throw new UnsupportedOperationException("Unimplemented method 'getOffset'");
            }

            @Override
            public Position getClausePosition() {
                throw new UnsupportedOperationException("Unimplemented method 'getClausePosition'");
            }
        };
    }

    @Override
    public LockClause lock() {
        return new LockClause() {
            @Override
            public Position getClausePosition() {
                return Position.AFTER_ORDER_BY;
            }

            @Override
            public String getLock(LockOptions lockOptions) {
                return "";  // SQLite는 행 잠금을 지원하지 않음
            }
        };
    }
}