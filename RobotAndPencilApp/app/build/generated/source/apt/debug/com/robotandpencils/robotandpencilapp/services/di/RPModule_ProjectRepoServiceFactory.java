package com.robotandpencils.robotandpencilapp.services.di;

import com.robotandpencils.robotandpencilapp.services.api.ProjectRepoService;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class RPModule_ProjectRepoServiceFactory implements Factory<ProjectRepoService> {
  private final RPModule module;

  public RPModule_ProjectRepoServiceFactory(RPModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public ProjectRepoService get() {  
    ProjectRepoService provided = module.projectRepoService();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ProjectRepoService> create(RPModule module) {  
    return new RPModule_ProjectRepoServiceFactory(module);
  }
}

