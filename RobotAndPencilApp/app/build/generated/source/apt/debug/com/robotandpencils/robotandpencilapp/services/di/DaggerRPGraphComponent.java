package com.robotandpencils.robotandpencilapp.services.di;

import com.robotandpencils.robotandpencilapp.services.RPService;
import com.robotandpencils.robotandpencilapp.services.RPService_MembersInjector;
import com.robotandpencils.robotandpencilapp.services.api.ProjectRepoService;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerRPGraphComponent implements RPGraphComponent {
  private Provider<ProjectRepoService> projectRepoServiceProvider;
  private MembersInjector<RPService> rPServiceMembersInjector;

  private DaggerRPGraphComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.projectRepoServiceProvider = ScopedProvider.create(RPModule_ProjectRepoServiceFactory.create(builder.rPModule));
    this.rPServiceMembersInjector = RPService_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), projectRepoServiceProvider);
  }

  @Override
  public void inject(RPService rpService) {  
    rPServiceMembersInjector.injectMembers(rpService);
  }

  public static final class Builder {
    private RPModule rPModule;
  
    private Builder() {  
    }
  
    public RPGraphComponent build() {  
      if (rPModule == null) {
        throw new IllegalStateException("rPModule must be set");
      }
      return new DaggerRPGraphComponent(this);
    }
  
    public Builder rPModule(RPModule rPModule) {  
      if (rPModule == null) {
        throw new NullPointerException("rPModule");
      }
      this.rPModule = rPModule;
      return this;
    }
  }
}

