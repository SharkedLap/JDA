/*
 * Copyright 2015-2019 Austin Keener, Michael Ritter, Florian Spieß, and the JDA contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dv8tion.jda.api.events.channel.category;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Indicates that a {@link PermissionOverride} of a {@link Category} has been deleted.
 *
 * <p>Can be used to retrieve the old override.
 */
public class CategoryDeleteOverrideEvent extends GenericCategoryEvent
{
    private final PermissionOverride override;

    public CategoryDeleteOverrideEvent(@Nonnull JDA api, long responseNumber, @Nonnull Category category, @Nonnull PermissionOverride override)
    {
        super(api, responseNumber, category);
        this.override = override;
    }

    /**
     * The affected {@link PermissionOverride} that was deleted.
     *
     * @return The override
     */
    @Nonnull
    public PermissionOverride getPermissionOverride()
    {
        return override;
    }

    /**
     * Whether this override was for a role.
     * <br>This means {@link #getRole()} is likely not null.
     *
     * @return True, if this override is for a role
     */
    public boolean isRoleOverride()
    {
        return override.isRoleOverride();
    }

    /**
     * Whether this override was for a member.
     * <br>Note that {@link #getMember()} might still be null if the member isn't cached or there is a discord inconsistency.
     *
     * @return True, if this override is for a member
     */
    public boolean isMemberOverride()
    {
        return override.isMemberOverride();
    }

    /**
     * The {@link IPermissionHolder} for the override.
     * <br>This can be a {@link Member} or {@link Role}. If the role or member are not cached then this will be null.
     *
     * @return Possibly-null permission holder
     */
    @Nullable
    public IPermissionHolder getPermissionHolder()
    {
        return isMemberOverride() ? override.getMember() : override.getRole();
    }

    /**
     * The {@link Member} for the override.
     * <br>This can be null if the member is not cached or there is a discord inconsistency.
     *
     * @return Possibly-null member
     */
    @Nullable
    public Member getMember()
    {
        return override.getMember();
    }

    /**
     * The {@link Role} for the override.
     *
     * @return Possibly-null role
     */
    @Nullable
    public Role getRole()
    {
        return override.getRole();
    }
}
