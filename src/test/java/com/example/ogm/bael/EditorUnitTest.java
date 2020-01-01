package com.example.ogm.bael;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EditorUnitTest {

    @Test
    void givenMongoDB_WhenEntitiesCreated_thenCanBeRetrieved() throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongodb");
        Editor editor = generateTestData();
        persistTestData(entityManagerFactory, editor);
        loadAndVerifyTestData(entityManagerFactory, editor);
    }

    private void persistTestData(EntityManagerFactory entityManagerFactory, Editor editor) throws Exception {
        TransactionManager transactionManager = com.arjuna.ats.jta.TransactionManager.transactionManager();
        EntityManager entityManager;

        transactionManager.begin();
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(editor);
        entityManager.close();
        transactionManager.commit();
    }

    private void loadAndVerifyTestData(EntityManagerFactory entityManagerFactory, Editor editor) throws Exception {
        TransactionManager transactionManager = com.arjuna.ats.jta.TransactionManager.transactionManager();
        EntityManager entityManager;

        transactionManager.begin();
        entityManager = entityManagerFactory.createEntityManager();
        Editor loadedEditor = entityManager.find(Editor.class, editor.getEditorId());
        assertNotNull(loadedEditor);
        assertEquals("Tom", loadedEditor.getEditorName());
        Map<String, Author> loadedAuthors = loadedEditor.getAssignedAuthors()
                .stream()
                .collect(Collectors.toMap(Author::getAuthorName, e -> e));
//        assertThat(loadedAuthors.get("Maria")
//            .getAuthoredArticles()).onProperty("articleTitle")
//                .containsOnly("Basic of Hibernate OGM");
//        assertThat(loadedAuthors.get("Mike")
//            .getAuthoredArticles()).onProperty("articleTitle")
//                .containsOnly("Intermediate of Hibernate OGM", "Advanced of Hibernate OGM");
        entityManager.close();
        transactionManager.commit();
    }

    private Editor generateTestData() {
        Editor tom = new Editor("Tom");
        Author maria = new Author("Maria");
        Author mike = new Author("Mike");
        Article basic = new Article("Basic of Hibernate OGM");
        Article intermediate = new Article("Intermediate of Hibernate OGM");
        Article advanced = new Article("Advanced of Hibernate OGM");
        maria.getAuthoredArticles()
                .add(basic);
        basic.setAuthor(maria);
        mike.getAuthoredArticles()
                .add(intermediate);
        intermediate.setAuthor(mike);
        mike.getAuthoredArticles()
                .add(advanced);
        advanced.setAuthor(mike);
        tom.getAssignedAuthors()
                .add(maria);
        maria.setEditor(tom);
        tom.getAssignedAuthors()
                .add(mike);
        mike.setEditor(tom);
        return tom;
    }
}