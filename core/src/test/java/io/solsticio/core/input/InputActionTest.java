package io.solsticio.core.input;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InputActionTest {
    
    private InputAction inputAction;
    @Mock
    private InputAdapter adapter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        inputAction = new InputAction(adapter);
    }

    @Test
    public void testIsPressed() {
        inputAction.add(Key.A);
        inputAction.add(Key.B);
        inputAction.add(Key.C);
        
        when(adapter.isClicked(Key.A)).thenReturn(false);
        when(adapter.isClicked(Key.B)).thenReturn(false);
        when(adapter.isClicked(Key.C)).thenReturn(true);
        assertTrue(inputAction.isClicked());
        
        when(adapter.isClicked(Key.A)).thenReturn(false);
        when(adapter.isClicked(Key.B)).thenReturn(false);
        when(adapter.isClicked(Key.C)).thenReturn(false);
        assertFalse(inputAction.isClicked());
    }

    @Test
    public void testIsClicked() {
        inputAction.add(Key.X);
        inputAction.add(Key.Y);
        inputAction.add(Key.Z);
        
        when(adapter.isPressed(Key.X)).thenReturn(false);
        when(adapter.isPressed(Key.Y)).thenReturn(false);
        when(adapter.isPressed(Key.Z)).thenReturn(true);
        assertTrue(inputAction.isPressed());
        
        when(adapter.isPressed(Key.X)).thenReturn(false);
        when(adapter.isPressed(Key.Y)).thenReturn(false);
        when(adapter.isPressed(Key.Z)).thenReturn(false);
        assertFalse(inputAction.isPressed());
    }

}
