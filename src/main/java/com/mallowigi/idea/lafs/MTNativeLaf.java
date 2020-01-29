/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.mallowigi.idea.lafs;

import com.intellij.ide.ui.laf.UIThemeBasedLookAndFeelInfo;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.xmlb.annotations.Transient;
import com.mallowigi.idea.themes.models.MTThemeable;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

/**
 * Look and Feel class for Dark Material Themes
 *
 * @author helio
 * Created on 2018-10-29
 */
@SuppressWarnings({"SerializableHasSerializationMethods",
  "MagicNumber"})
public final class MTNativeLaf extends DarculaLaf {

  /**
   * Service to install properties in UIManager
   */
  @Transient
  private final MTLafInstaller mtLafInstaller;
  @Transient
  private final UIManager.LookAndFeelInfo currentLookAndFeel;

  /**
   * Represents a Material Dark Look And Feel
   *
   * @param theme           of type MTThemeable
   * @param lookAndFeelInfo current look and feel
   */
  public MTNativeLaf(@NotNull final MTThemeable theme, final UIManager.LookAndFeelInfo lookAndFeelInfo) {
    mtLafInstaller = new MTLafInstaller(theme);
    currentLookAndFeel = lookAndFeelInfo;
  }

  /**
   * Install additional Darcula defaults
   *
   * @param defaults of type UIDefaults
   */
  @SuppressWarnings({"DuplicateStringLiteralInspection",
    "UnstableApiUsage"})
  private static void installDarculaDefaults(@NonNls final UIDefaults defaults) {
    defaults.put("darcula.primary", new ColorUIResource(0x3c3f41));
    defaults.put("darcula.contrastColor", new ColorUIResource(0x262626));

    defaults.put("grayFilter", new UIUtil.GrayFilter(-100, -100, 100));
    defaults.put("text.grayFilter", new UIUtil.GrayFilter(-15, -10, 100));
  }

  /**
   * Installs and returns the defaults for dark lafs
   *
   * @return the defaults (type UIDefaults) of this MTDarkLaf object.
   */
  @Override
  public UIDefaults getDefaults() {
    final UIDefaults defaults = super.getDefaults();

    //    MTLafInstaller.installDefaults(defaults);
    // Install material defaults
    MTLafInstaller.installMTDefaults(defaults);

    return defaults;
  }

  @Override
  protected void loadDefaults(final UIDefaults defaults) {
    ((UIThemeBasedLookAndFeelInfo) currentLookAndFeel).installTheme(defaults);
  }

}
