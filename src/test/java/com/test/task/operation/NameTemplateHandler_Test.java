package com.test.task.operation;

import com.test.task.entity.Lector;
import com.test.task.operations.impl.NameTemplateHandler;
import com.test.task.service.LectorService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class NameTemplateHandler_Test {
    private static final String INPUT_EXISTING_TEMPLATE = " Global search by ohn";
    private static final String INPUT_NON_EXISTING_TEMPLATE = " Global search by van";
    private static final String EXPECTED = "John Bla, Johnatan Blabla, Fred Johnson" + System.lineSeparator();

    private List<Lector> lectors;
    @InjectMocks
    private NameTemplateHandler nameTemplateHandler;
    @Mock
    private LectorService lectorService;

    @BeforeAll
    public void setup() {
        lectors = new ArrayList<>();
        String[] firstNames = new String[]{"John", "Johnatan", "Fred"};
        String[] lastNames = new String[]{"Bla", "Blabla", "Johnson"};
        Lector lector;
        for (int i = 0; i < firstNames.length; i++) {
            lector = new Lector();
            lector.setFirstName(firstNames[i]);
            lector.setLastName(lastNames[i]);
            lectors.add(lector);
        }
    }

    @AfterAll
    public void close() {}

    @Test
    public void handle_Ok() {
        Mockito.when(lectorService.findWhereFirstOrLastNameLike("ohn")).thenReturn(lectors);
        Mockito.when(lectorService.findWhereFirstOrLastNameLike("van")).thenReturn(new ArrayList<>());
        String actual = nameTemplateHandler.handle(INPUT_EXISTING_TEMPLATE);
        String actualEmpty = nameTemplateHandler.handle(INPUT_NON_EXISTING_TEMPLATE);
        assertEquals(EXPECTED, actual);
        assertTrue(actualEmpty.isEmpty());
    }
}
