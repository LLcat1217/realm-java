package io.realm;


import android.util.JsonReader;
import io.realm.ImportFlag;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>(2);
        modelClasses.add(some.test.AllTypes.class);
        modelClasses.add(some.test.Simple.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap<Class<? extends RealmModel>, OsObjectSchemaInfo>(2);
        infoMap.put(some.test.AllTypes.class, io.realm.some_test_AllTypesRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(some.test.Simple.class, io.realm.some_test_SimpleRealmProxy.getExpectedObjectSchemaInfo());
        return infoMap;
    }

    @Override
    public ColumnInfo createColumnInfo(Class<? extends RealmModel> clazz, OsSchemaInfo schemaInfo) {
        checkClass(clazz);

        if (clazz.equals(some.test.AllTypes.class)) {
            return io.realm.some_test_AllTypesRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(some.test.Simple.class)) {
            return io.realm.some_test_SimpleRealmProxy.createColumnInfo(schemaInfo);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getSimpleClassNameImpl(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(some.test.AllTypes.class)) {
            return "AllTypes";
        }
        if (clazz.equals(some.test.Simple.class)) {
            return "Simple";
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public Class<? extends RealmModel> getClazzImpl(String className) {
        checkClassName(className);

        if (className.equals("AllTypes")) {
            return some.test.AllTypes.class;
        }
        if (className.equals("Simple")) {
            return some.test.Simple.class;
        }
        throw getMissingProxyClassException(className);
    }

    @Override
    public boolean hasPrimaryKeyImpl(Class<? extends RealmModel> clazz) {
        return some.test.AllTypes.class.isAssignableFrom(clazz);
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(some.test.AllTypes.class)) {
                return clazz.cast(new io.realm.some_test_AllTypesRealmProxy());
            }
            if (clazz.equals(some.test.Simple.class)) {
                return clazz.cast(new io.realm.some_test_SimpleRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(some.test.AllTypes.class)) {
            some_test_AllTypesRealmProxy.AllTypesColumnInfo columnInfo = (some_test_AllTypesRealmProxy.AllTypesColumnInfo) realm.getSchema().getColumnInfo(some.test.AllTypes.class);
            return clazz.cast(io.realm.some_test_AllTypesRealmProxy.copyOrUpdate(realm, columnInfo, (some.test.AllTypes) obj, update, cache, flags));
        }
        if (clazz.equals(some.test.Simple.class)) {
            some_test_SimpleRealmProxy.SimpleColumnInfo columnInfo = (some_test_SimpleRealmProxy.SimpleColumnInfo) realm.getSchema().getColumnInfo(some.test.Simple.class);
            return clazz.cast(io.realm.some_test_SimpleRealmProxy.copyOrUpdate(realm, columnInfo, (some.test.Simple) obj, update, cache, flags));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public long insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(some.test.AllTypes.class)) {
            return io.realm.some_test_AllTypesRealmProxy.insert(realm, (some.test.AllTypes) object, cache);
        } else if (clazz.equals(some.test.Simple.class)) {
            return io.realm.some_test_SimpleRealmProxy.insert(realm, (some.test.Simple) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(some.test.AllTypes.class)) {
                io.realm.some_test_AllTypesRealmProxy.insert(realm, (some.test.AllTypes) object, cache);
            } else if (clazz.equals(some.test.Simple.class)) {
                io.realm.some_test_SimpleRealmProxy.insert(realm, (some.test.Simple) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(some.test.AllTypes.class)) {
                    io.realm.some_test_AllTypesRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(some.test.Simple.class)) {
                    io.realm.some_test_SimpleRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public long insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(some.test.AllTypes.class)) {
            return io.realm.some_test_AllTypesRealmProxy.insertOrUpdate(realm, (some.test.AllTypes) obj, cache);
        } else if (clazz.equals(some.test.Simple.class)) {
            return io.realm.some_test_SimpleRealmProxy.insertOrUpdate(realm, (some.test.Simple) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(some.test.AllTypes.class)) {
                io.realm.some_test_AllTypesRealmProxy.insertOrUpdate(realm, (some.test.AllTypes) object, cache);
            } else if (clazz.equals(some.test.Simple.class)) {
                io.realm.some_test_SimpleRealmProxy.insertOrUpdate(realm, (some.test.Simple) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(some.test.AllTypes.class)) {
                    io.realm.some_test_AllTypesRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(some.test.Simple.class)) {
                    io.realm.some_test_SimpleRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
            throws JSONException {
        checkClass(clazz);

        if (clazz.equals(some.test.AllTypes.class)) {
            return clazz.cast(io.realm.some_test_AllTypesRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(some.test.Simple.class)) {
            return clazz.cast(io.realm.some_test_SimpleRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
            throws IOException {
        checkClass(clazz);

        if (clazz.equals(some.test.AllTypes.class)) {
            return clazz.cast(io.realm.some_test_AllTypesRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(some.test.Simple.class)) {
            return clazz.cast(io.realm.some_test_SimpleRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(some.test.AllTypes.class)) {
            return clazz.cast(io.realm.some_test_AllTypesRealmProxy.createDetachedCopy((some.test.AllTypes) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(some.test.Simple.class)) {
            return clazz.cast(io.realm.some_test_SimpleRealmProxy.createDetachedCopy((some.test.Simple) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> boolean isEmbedded(Class<E> clazz) {
        if (clazz.equals(some.test.AllTypes.class)) {
            return false;
        }
        if (clazz.equals(some.test.Simple.class)) {
            return false;
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> void updateEmbeddedObject(Realm realm, E unmanagedObject, E managedObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) managedObject.getClass().getSuperclass();

        if (clazz.equals(some.test.AllTypes.class)) {
            throw getNotEmbeddedClassException("some.test.AllTypes");
        } else if (clazz.equals(some.test.Simple.class)) {
            throw getNotEmbeddedClassException("some.test.Simple");
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}