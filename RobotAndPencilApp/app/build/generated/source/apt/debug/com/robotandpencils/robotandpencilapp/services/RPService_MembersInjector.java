package com.robotandpencils.robotandpencilapp.services;

import android.app.IntentService;
import com.robotandpencils.robotandpencilapp.services.api.ProjectRepoService;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class RPService_MembersInjector implements MembersInjector<RPService> {
  private final MembersInjector<IntentService> supertypeInjector;
  private final Provider<ProjectRepoService> projectRepoServiceProvider;

  public RPService_MembersInjector(MembersInjector<IntentService> supertypeInjector, Provider<ProjectRepoService> projectRepoServiceProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert projectRepoServiceProvider != null;
    this.projectRepoServiceProvider = projectRepoServiceProvider;
  }

  @Override
  public void injectMembers(RPService instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.projectRepoService = projectRepoServiceProvider.get();
  }

  public static MembersInjector<RPService> create(MembersInjector<IntentService> supertypeInjector, Provider<ProjectRepoService> projectRepoServiceProvider) {  
      return new RPService_MembersInjector(supertypeInjector, projectRepoServiceProvider);
  }
}

