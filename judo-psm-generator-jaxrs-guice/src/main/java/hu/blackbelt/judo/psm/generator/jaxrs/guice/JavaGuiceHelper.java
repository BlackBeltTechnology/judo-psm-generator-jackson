package hu.blackbelt.judo.psm.generator.jaxrs.guice;

/*-
 * #%L
 * JUDO PSM Generator Jakarta RESTful Web Services Guiceementation
 * %%
 * Copyright (C) 2018 - 2023 BlackBelt Technology
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import hu.blackbelt.judo.generator.commons.StaticMethodValueResolver;
import hu.blackbelt.judo.generator.commons.ThreadLocalContextHolder;
import hu.blackbelt.judo.generator.commons.annotations.TemplateHelper;
import hu.blackbelt.judo.meta.psm.namespace.NamedElement;
import hu.blackbelt.judo.meta.psm.namespace.Namespace;

import java.util.Arrays;
import java.util.stream.Collectors;

import static hu.blackbelt.judo.psm.generator.jaxrs.api.JavaApiHelper.*;
import static hu.blackbelt.judo.psm.generator.jaxrs.api.JavaNamespaceHelper.*;

@TemplateHelper
public class JavaGuiceHelper extends StaticMethodValueResolver {

    public static synchronized String getGuicePrefixLocal() {
        return (String) ThreadLocalContextHolder.getVariable("guicePrefix");
    }

    public static String guicePackageName() {
        return (getGuicePrefixLocal().equals("") ? "" : getGuicePrefixLocal() + ".");
    }

    public static String namedElementGuiceParentPath(NamedElement namedElement) {
        return guicePackageName().replaceAll("\\.", "/") + namedElementParentPath(namedElement);
    }

    public static String namedElementRestPath(NamedElement namedElement) {
        return Arrays.stream(fqName((Namespace) namedElement.eContainer(), "/", false).split("/")).filter(name -> !DEFAULT_TRANSFER_OBJECT_TYPES.equals(name)).collect(Collectors.joining("/"));
    }

    public static String namedElementGuicePackageName(NamedElement namedElement) {
        return guicePackageName() + namedElementPackageName(namedElement);
    }

    public static String namedElementGuiceRestPackageName(NamedElement namedElement) {
        return guicePackageName() + REST + "." + namedElementPackageName(namedElement);
    }

    public static String namedElementGuiceRestParentPath(NamedElement namedElement) {
        return guicePackageName().replaceAll("\\.", "/") + REST + "/" + namedElementParentPath(namedElement);
    }

    public static String applicationGuiceClassName(NamedElement namedElement) {
        return applicationClassName(namedElement) + "Guice";
    }

    public static String guiceClassName(NamedElement namedElement) {
        return className(namedElement) + "Guice";
    }

    public static String variableName(NamedElement namedElement) {
        return (namedElementPackageName(namedElement) + "_" + guiceClassName(namedElement)).replaceAll("\\.", "_");
    }

    public static String applicationGuiceFqName(NamedElement namedElement) {
        return namedElementGuicePackageName(namedElement) + "." + applicationGuiceClassName(namedElement);
    }

    public static String namedElementGuiceRestFqName(NamedElement namedElement) {
        return namedElementGuiceRestPackageName(namedElement) + "." + guiceClassName(namedElement);
    }

    public static String guiceProviderClassName(NamedElement namedElement) {
        return className(namedElement) + "RestServiceProvider";
    }
    public static String guiceRestApplicationModuleConfigurator(NamedElement namedElement) {
        return className(namedElement) + "RestApplicationModuleConfigurator";
    }

    public static String guiceRestApplicationModuleConfiguration(NamedElement namedElement) {
        return className(namedElement) + "RestApplicationModuleConfiguration";
    }

    public static String guiceRestApplicationModules(NamedElement namedElement) {
        return className(namedElement) + "RestApplicationModules";
    }

    public static String guiceRestApplicationModulesBuilder(NamedElement namedElement) {
        return className(namedElement) + "RestApplicationModulesBuilder";
    }

    public static String guiceRestApplication(NamedElement namedElement) {
        return className(namedElement) + "RestApplication";
    }

    public static String guiceRestApplicationBuilder(NamedElement namedElement) {
        return className(namedElement) + "RestApplicationBuilder";
    }

    public static String guiceRestApplicationProperties(NamedElement namedElement) {
        return className(namedElement) + "RestApplicationProperties";
    }
}
