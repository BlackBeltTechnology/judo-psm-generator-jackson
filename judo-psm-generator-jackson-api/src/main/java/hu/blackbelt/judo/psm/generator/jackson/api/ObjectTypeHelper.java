package hu.blackbelt.judo.psm.generator.jackson.api;

/*-
 * #%L
 * JUDO PSM Generator SDK Core API
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
import hu.blackbelt.judo.generator.commons.annotations.TemplateHelper;
import hu.blackbelt.judo.meta.psm.accesspoint.AbstractActorType;
import hu.blackbelt.judo.meta.psm.data.EntityType;
import hu.blackbelt.judo.meta.psm.namespace.Model;
import hu.blackbelt.judo.meta.psm.service.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static hu.blackbelt.judo.psm.generator.jackson.api.ModelHelper.getSpecifiedContainer;
import static hu.blackbelt.judo.psm.generator.jackson.api.ModelHelper.modelWrapper;


@TemplateHelper
public class ObjectTypeHelper extends StaticMethodValueResolver {

    @Override
    public Object resolve(final Object context) {
        return UNRESOLVED;
    }

    @Override
    public Set<Map.Entry<String, Object>> propertySet(Object context) {
        return new HashSet<>();
    }

    public static boolean isActorType(TransferObjectType transferObjectType) {
        return transferObjectType.getActorType() != null;
    }

    public static boolean isOptional(TransferObjectType transferObjectType) {
        return transferObjectType.isOptional();
    }

    public static boolean isQueryCustomizer(TransferObjectType transferObjectType) {
        return transferObjectType.isQueryCustomizer();
    }

    public static EntityType getEntity(TransferObjectType transferObjectType) {
        Model model = getSpecifiedContainer(transferObjectType, Model.class);
        return modelWrapper(model).getStreamOfPsmDataEntityType()
                .filter(e -> e.getDefaultRepresentation() == transferObjectType).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Entity representation not found: " + transferObjectType.getName()));
    }

    public static boolean isEntity(TransferObjectType transferObjectType) {
        Model model = getSpecifiedContainer(transferObjectType, Model.class);
        return modelWrapper(model).getStreamOfPsmDataEntityType()
                .filter(e -> e.getDefaultRepresentation() == transferObjectType).findFirst().isPresent();
    }

    public static boolean isFaultType(TransferObjectType transferObjectType) {
        Model model = getSpecifiedContainer(transferObjectType, Model.class);
        return modelWrapper(model)
                .getStreamOfPsmServiceTransferOperation()
                .anyMatch(o ->
                        o.getFaults().stream().map(f -> f.getType()).filter(t -> t == transferObjectType).findAny().isPresent()
                );
    }

    public static boolean isMapped(TransferObjectType transferObjectType) {
        return (transferObjectType instanceof MappedTransferObjectType);
    }

    public static boolean hasOperation(TransferObjectType transferObjectType) {
        return transferObjectType.getOperations().size() > 0;
    }

    public static boolean hasAttribute(TransferObjectType transferObjectType) {
        return transferObjectType.getAttributes().size() > 0;
    }


    public static boolean hasCustomOperation(TransferObjectType transferObjectType) {
        return transferObjectType.getOperations().stream().filter(OperationHelper::isCustomOperation).count() > 0;
    }


/*
    public static void collectOperations(TransferObjectType transferObjectType, Set<TransferOperation> operations) {

        operations.addAll(transferObjectType.getOperations());
        if (isMapped(transferObjectType)) {
            MappedTransferObjectType mappedTransferObjectType = (MappedTransferObjectType) transferObjectType;
            EntityType entityType = mappedTransferObjectType.getEntityType();
            for (EntityType superType : entityType.getSuperEntityTypes()) {
            }
        }
        if (transferObjectType.)
    }
*/

    public static List<TransferOperation> allOperations(TransferObjectType transferObjectType) {
        return transferObjectType.getOperations().stream()
                .filter(o -> o.getBehaviour() == null)
                .collect(Collectors.toList());
    }

}
