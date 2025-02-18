/*
 * Created on 07/ott/2011
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * 
 * This file is part of the Sejda source code
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sejda.core.support.prefix.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sejda.core.support.prefix.model.NameGenerationRequest.nameRequest;

import org.junit.jupiter.api.Test;

/**
 * @author Andrea Vacondio
 * 
 */
public class StrictBookmarkPrefixProcessorTest extends BasePrefixProcessorTest {
    private StrictBookmarkPrefixProcessor victim = new StrictBookmarkPrefixProcessor();

    @Override
    public PrefixProcessor getProcessor() {
        return victim;
    }

    @Test
    public void nullBookmarks() {
        String prefix = "prefix_[BOOKMARK_NAME_STRICT]_[BASENAME]";
        assertEquals(prefix, victim.process(prefix, nameRequest()));
    }

    @Test
    public void testComplexProcess() {
        String prefix = "prefix_[BOOKMARK_NAME_STRICT]_[BASENAME]";
        String bookmark = "book name here ";
        String expected = "prefix_book name here_[BASENAME]";
        assertEquals(expected, victim.process(prefix, nameRequest().bookmark(bookmark)));
    }

    @Test
    public void testComplexProcessInvalidChars() {
        String prefix = "prefix_[BOOKMARK_NAME_STRICT]_[BASENAME]";
        String bookmark = "book<>?$ç°";
        String expected = "prefix_book_[BASENAME]";
        assertEquals(expected, victim.process(prefix, nameRequest().bookmark(bookmark)));
    }
}
