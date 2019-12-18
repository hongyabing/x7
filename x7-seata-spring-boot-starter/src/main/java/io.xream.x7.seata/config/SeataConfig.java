/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.xream.x7.seata.config;

import io.seata.rm.datasource.DataSourceProxy;
import io.xream.x7.reyc.api.SimpleRestTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

public class SeataConfig {

    @ConditionalOnBean(SimpleRestTemplate.class)
    @Bean
    public SeataInterceptor seataInterceptor(SimpleRestTemplate simpleRestTemplate){
        SeataInterceptor seataInterceptor = new SeataInterceptor();
        simpleRestTemplate.add(seataInterceptor);
        return seataInterceptor;
    }


    @Lazy
    @ConditionalOnBean(DataSource.class)
    @Primary
    @Bean("dataSource")
    public DataSource dataSource(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }
}