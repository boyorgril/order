package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.CollectFood;
import com.groupwork.order.datasource.dto.CollectFoodExample;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class CollectFoodSqlProvider {

    public String countByExample(CollectFoodExample example){
        SQL sql = new SQL();
        sql.SELECT("count(*)");
        sql.FROM("collectFood");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        return sql.toString();
    }

    public String insert(CollectFood record){
        SQL sql = new SQL();
        sql.INSERT_INTO("collectFood");

        if(record.getSfid() != null){
            sql.VALUES("sfid", "#{sfid,jdbcType=BIGINT}");
        }

        if(record.getUserId() != null){
            sql.VALUES("userId", "#{userId,jdbcType=BIGINT}");
        }

        if(record.getCreateAt() != null){
            sql.VALUES("createAt", "#{userId,jdbcType=TIMESTAMP}");
        }

        if(record.getStatus() != null){
            sql.VALUES("status", "#{status,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }


    public String selectByExample(CollectFoodExample example){
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("sfid");
        sql.SELECT("userId");
        sql.SELECT("createAt");
        sql.SELECT("status");
        sql.FROM("collectFood");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        return sql.toString();

    }


    public String selectById(Long id){
        SQL sql = new SQL();
        sql.SELECT("id");
        sql.SELECT("sfid");
        sql.SELECT("userId");
        sql.SELECT("createAt");
        sql.SELECT("status");
        sql.FROM("collectFood");
        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter){
        SQL sql = new SQL();
        CollectFood record = (CollectFood) parameter.get("record");
        CollectFoodExample example = (CollectFoodExample) parameter.get("example");
        sql.UPDATE("collectFood");

        if(record.getId() != null){
            sql.SET("id", "#{id,jdbcType=BIGINT}");
        }

        if(record.getSfid() != null){
            sql.SET("sfid", "#{sfid,jdbcType=BIGINT}");
        }

        if(record.getUserId() != null){
            sql.SET("userId", "#{userId,jdbcType=BIGINT}");
        }

        if(record.getCreateAt() != null){
            sql.SET("createAt", "#{userId,jdbcType=TIMESTAMP}");
        }

        if(record.getStatus() != null){
            sql.SET("status", "#{status,jdbcType=VARCHAR}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter){

        SQL sql = new SQL();
        sql.UPDATE("collectFood");

        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("sfid = #{record.sfid,jdbcType=BIGINT}");
        sql.SET("userId = #{record.userId,jdbcType=BIGINT}");
        sql.SET("createAt = #{record.createdAt,jdbcType=TIMESTAMP}");
        sql.SET("status = #{record.status,jdbcType=VARCHAR}");

        CollectFoodExample example = (CollectFoodExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();

    }

    public String updateByPrimaryKeySelective(CollectFood record){
        SQL sql = new SQL();
        sql.UPDATE("collectFood");

        if(record.getSfid() != null){
            sql.SET("sfid", "#{sfid,jdbcType=BIGINT}");
        }

        if(record.getUserId() != null){
            sql.SET("userId", "#{userId,jdbcType=BIGINT}");
        }

        if(record.getCreateAt() != null){
            sql.SET("createAt", "#{userId,jdbcType=TIMESTAMP}");
        }

        if(record.getStatus() != null){
            sql.SET("status", "#{status,jdbcType=VARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        return sql.toString();
    }

    protected void applyWhere(SQL sql, CollectFoodExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }

        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }

        StringBuilder sb = new StringBuilder();
        List<CollectFoodExample.Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            CollectFoodExample.Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }

                sb.append('(');
                List<CollectFoodExample.Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    CollectFoodExample.Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }

                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }

        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}
