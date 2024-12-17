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

import com.github.jknack.handlebars.internal.lang3.StringUtils;
import hu.blackbelt.judo.generator.commons.StaticMethodValueResolver;
import hu.blackbelt.judo.generator.commons.ThreadLocalContextHolder;
import hu.blackbelt.judo.generator.commons.annotations.TemplateHelper;
import hu.blackbelt.judo.meta.psm.namespace.Model;
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
    public static synchronized String getGuiceSdkPrefixLocal() {
        return (String) ThreadLocalContextHolder.getVariable("guiceSdkPrefix");
    }

    public static String guicePackageName() {
        return (getGuicePrefixLocal().equals("") ? "" : getGuicePrefixLocal() + ".");
    }

    public static String guiceSdkPackageName() {
        return (getGuiceSdkPrefixLocal().equals("") ? "" : getGuiceSdkPrefixLocal() + ".");
    }

    public static String namedElementGuiceParentPath(NamedElement namedElement) {
        return guicePackageName().replaceAll("\\.", "/") + namedElementParentPath(namedElement);
    }

    public static String namedElementGuicePackageName(NamedElement namedElement) {
        return guicePackageName() + namedElementPackageName(namedElement);
    }

    public static String namedElementGuiceRestPackageName(NamedElement namedElement) {
        return guicePackageName() + REST + "." + namedElementPackageName(namedElement);
    }

    public static String modelGuiceRestPackageName(Model model) {
        return guicePackageName() + REST + "." + model.getName().toLowerCase();
    }

    public static String modelGuiceRestPath(Model model) {
        return guicePackageName().replaceAll("\\.", "/")  + REST + "/" + model.getName().toLowerCase();
    }

    public static String namedElementGuiceRestParentPath(NamedElement namedElement) {
        return guicePackageName().replaceAll("\\.", "/") + REST + "/" + namedElementParentPath(namedElement);
    }

    public static String modelGuiceRestParentPath(Model model) {
        return guicePackageName().replaceAll("\\.", "/") + REST + "/" + model.getName().toLowerCase();
    }

    public static String applicationGuiceClassName(NamedElement namedElement) {
        return applicationClassName(namedElement) + "Guice";
    }

    public static String guiceClassName(NamedElement namedElement) {
        return className(namedElement) + "Guice";
    }

    public static String applicationGuiceFqName(NamedElement namedElement) {
        return namedElementGuicePackageName(namedElement) + "." + applicationGuiceClassName(namedElement);
    }

    public static String namedElementGuiceRestFqName(NamedElement namedElement) {
        return namedElementGuiceRestPackageName(namedElement) + "." + guiceClassName(namedElement);
    }

    public static String guiceRestServiceProviderClassName(NamedElement namedElement) {
        return className(namedElement) + "RestServiceProvider";
    }

    public static String guiceRestServiceProviderClassFqName(NamedElement namedElement) {
        return namedElementGuiceRestPackageName(namedElement) + "." + guiceRestServiceProviderClassName(namedElement);
    }

    public static String guiceRestApplicationProviderClassName(NamedElement namedElement) {
        return className(namedElement) + "RestApplicationProvider";
    }

    public static String guiceRestApplicationProviderFqName(NamedElement namedElement) {
        return namedElementGuiceRestPackageName(namedElement) + "." + guiceRestApplicationProviderClassName(namedElement);
    }

    public static String guiceRestApplicationModuleConfigurator(Model model) {
        return StringUtils.capitalize(model.getName()) + "RestApplicationModuleConfigurator";
    }

    public static String guiceRestApplicationModuleConfiguration(Model model) {
        return StringUtils.capitalize(model.getName()) + "RestApplicationModuleConfiguration";
    }

    public static String guiceRestApplicationModules(Model model) {
        return StringUtils.capitalize(model.getName()) + "RestApplicationModules";
    }

    public static String guiceRestApplicationModulesBuilder(Model model) {
        return StringUtils.capitalize(model.getName()) + "RestApplicationModulesBuilder";
    }

    public static String guiceRestApplication(Model model) {
        return StringUtils.capitalize(model.getName()) + "RestApplication";
    }

    public static String guiceRestApplicationBuilder(Model model) {
        return StringUtils.capitalize(model.getName()) + "RestApplicationBuilder";
    }

    public static String guiceRestApplicationProperties(Model model) {
        return StringUtils.capitalize(model.getName()) + "RestApplicationProperties";
    }

//    public static String guiceRestApplicationProvider(Model model) {
//        return className(model) + "RestApplicationProvider";
//    }
//
//    public static String guiceRestApplicationProviderFqName(Model model) {
//        return modelGuiceRestPackageName(model) + "." + guiceRestApplicationProvider(model);
//    }

    public static String guiceDaoProviderModuleName(Model model) {
        return StringUtils.capitalize(model.getName()) + "DaoModules";
    }

    public static String guiceDaoProviderModuleFqName(Model model) {
        return guiceSdkPackageName() + guiceDaoProviderModuleName(model);
    }
}
