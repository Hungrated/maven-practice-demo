package edu.zju.cst.myproject.util;

import edu.zju.cst.myproject.model.User;
import junit.framework.Assert;

public class EntitiesHelper {

    private static User baseUser = new User("admin", "Ningbo");

    public static void assertUser(User expected, User actual) {
        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getAddress(), actual.getAddress());
    }

    public static void assertUser(User expected) {
        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getName(), baseUser.getName());
        Assert.assertEquals(expected.getAddress(), baseUser.getAddress());
    }
}
