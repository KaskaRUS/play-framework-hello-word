package space.zhdanov.laboratory.carshop.modules;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import play.db.Database;
import space.zhdanov.laboratory.carshop.repositories.ItemRepository;
import space.zhdanov.laboratory.carshop.repositories.MarkRepository;
import space.zhdanov.laboratory.carshop.repositories.ModelRepository;

import javax.sql.DataSource;

public class MyBatisModule extends org.mybatis.guice.MyBatisModule {

    @Override
    protected void initialize() {
        environmentId("development");
        bindConstant().annotatedWith(Names.named("mybatis.configuration.failFast")).to(true);
        bindDataSourceProviderType(PlayDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);

        addMapperClass(MarkRepository.class);
        addMapperClass(ItemRepository.class);
        addMapperClass(ModelRepository.class);
        addTypeHandlerClass(LocalDateTypeHandler.class);
    }

    @Singleton
    public static class PlayDataSourceProvider implements Provider<DataSource> {
        private final Database db;

        @Inject
        public PlayDataSourceProvider(final Database db) {
            this.db = db;
        }


        @Override
        public DataSource get() {
            return db.getDataSource();
        }
    }

}