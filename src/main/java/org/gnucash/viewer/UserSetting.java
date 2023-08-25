package org.gnucash.viewer;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * User setting persistence.
 * 
 * @author rshkw
 *
 */
public class UserSetting {
  private static final Logger LOGGER = Logger.getLogger(Class.class.getName());
  public static String NodePrefName = "org.gnucash.viewer";

  private String c_InputFile = "InputFile";
  private String m_InputFile = "";

  private Preferences pref;
  private Preferences userPrefs = Preferences.userRoot();

  /**
   * Constructor Initialize settings
   */
  public UserSetting() {
    // Navigate to the preference node that stores the user setting
    pref = userPrefs.node(NodePrefName);

    m_InputFile = pref.get(c_InputFile, "");
  }

  public String get_InputFile() {
    return this.m_InputFile;
  }

  public Preferences getPreferences() {
    return this.pref;
  }

  public void set_InputFile(File a_InputFile) {
    pref.put(c_InputFile, a_InputFile.getAbsolutePath());
    this.m_InputFile = a_InputFile.getAbsolutePath();
  }

  /**
   * Save all settings
   */
  public void save() {
    try {
      ;
      pref.put(c_InputFile, m_InputFile);

      pref.flush();
    } catch (BackingStoreException e) {
      LOGGER.log(Level.INFO, e.getMessage());
    }
  }

  public String print() {
    String l_line = "User setting \n";
    l_line = l_line + "Name: " + pref.name() + "\n";
    l_line = l_line + c_InputFile + ": " + m_InputFile + "\n";
    return l_line;
  }
}
